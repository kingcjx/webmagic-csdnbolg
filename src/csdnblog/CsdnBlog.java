package csdnblog;

public class CsdnBlog {
	
	private String href;// 链接

	private String title;// 标题
	private String userName;

	private String date;// 日期

	private String answer;// 回答数量

	private String category;// 分类

	private int view;// 阅读人数

	private int cMoney;// 选项


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public int getcMoney() {
		return cMoney;
	}

	public void setcMoney(int cMoney) {
		this.cMoney = cMoney;
	}



}
