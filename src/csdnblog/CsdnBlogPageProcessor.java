package csdnblog;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;


public class CsdnBlogPageProcessor implements PageProcessor {

	private static int size = 0;
	// 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
		
		if((page.getUrl().regex("https://ask.csdn.net/questions/\\d+$").match())){
			size++;
			CsdnBlog csdnBlog = handlerData(page);
			new CsdnBlogDao().add(csdnBlog);
			System.out.println("获取问答页面"+size+":"+csdnBlog.getHref());
		}else{
			page.addTargetRequests(page.getHtml().xpath("//div[@class='questions_detail_con']").links().all());
		}
	}

	private CsdnBlog handlerData(Page page) {
		CsdnBlog csdnBlog = new CsdnBlog();
		csdnBlog.setHref(page.getUrl()+"");
		csdnBlog.setTitle(page.getHtml().xpath("title/text()").get());
		csdnBlog.setUserName(page.getHtml().xpath("//div[@class='user_name']").get());
		csdnBlog.setDate(page.getHtml().xpath("//*[@id=\"questions-show\"]/div[3]/div[2]/div[1]/div[1]/p/span/text()").get());
		csdnBlog.setView(Integer.parseInt(page.getHtml().xpath("//*[@id=\"question_686178\"]/a[2]").get()));
		return csdnBlog;
	}

	// 把list转换为string，用,分割
	public static String listToString(List<String> stringList) {
		if (stringList == null) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		boolean flag = false;
		for (String string : stringList) {
			if (flag) {
				result.append(",");
			} else {
				flag = true;
			}
			result.append(string);
		}
		return result.toString();
	}

	public static void main(String[] args) {
		System.out.println("begin to get data");
		Spider.create(new CsdnBlogPageProcessor()).addUrl("https://ask.csdn.net/").thread(5).run();
		System.out.println("the end !");
	}
}
