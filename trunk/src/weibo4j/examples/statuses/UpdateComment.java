/**
 * 
 */
package weibo4j.examples.statuses;

import weibo4j.Comment;
import weibo4j.Status;
import weibo4j.Weibo;

/**
 * @author hezhou
 *
 */
public class UpdateComment {

	/**
	 * 对一条微博信息进行评论
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
    	System.setProperty("weibo4j.oauth.consumerSecret", Weibo.CONSUMER_SECRET);
        try {
        	Weibo weibo = getWeibo(true,args);
        	Status status = weibo.updateStatus("撒旦撒旦顶顶顶顶顶顶，，");
        	Thread.sleep(1000);
        	String sid = status.getId()+"";
        	Comment comment = weibo.updateComment("踩踩踩踩踩", sid, null);
        	System.out.println(comment.getId() + " : " + comment.getText() + "  " + comment.getCreatedAt());
        	Thread.sleep(1000);
        	comment = weibo.updateComment("踩踩踩踩踩1", sid, null);
        	System.out.println(comment.getId() + " : " + comment.getText() + "  " + comment.getCreatedAt());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Weibo getWeibo(boolean isOauth,String ... args) {
		Weibo weibo = new Weibo();
		if(isOauth) {//oauth验证方式 args[0]:访问的token；args[1]:访问的密匙
			//weibo.setToken(args[0], args[1]);
			//weibo.setToken("cba6194506eccede1b311c503e9c971e", "4e68230eb0e55ec3e22b1025c327b9d1");
			weibo.setToken("95905141d9dc7ca9beb87199fb295118", "4489372238ec36cbc111d252eb3dc484");
		}else {//用户登录方式
    		weibo.setUserId(args[0]);//用户名/ID
    		weibo.setPassword(args[1]);//密码
		}
		return weibo;
	}

}
