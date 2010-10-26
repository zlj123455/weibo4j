/**
 * 
 */
package weibo4j.examples.timeline;

import java.util.List;

import weibo4j.Comment;
import weibo4j.Status;
import weibo4j.Weibo;

/**
 * @author hezhou
 *
 */
public class GetComments {

	/**
	 * 返回指定微博的最新n条评论 
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
    	System.setProperty("weibo4j.oauth.consumerSecret", Weibo.CONSUMER_SECRET);
        try {
        	Weibo weibo = getWeibo(true,args);
        	//args[2]:用户id
        	List<Status> list = weibo.getUserTimeline("1748033497");
        	if(list.size() > 0) {
        		//最新一条微博信息id
        		String sid = list.get(0).getId()+"";
        		//对该微博消息添加评论
        		//weibo.updateComment("测试评论", sid , null);
        		
        		List<Comment> comments = weibo.getComments(sid);
        		for(Comment comment : comments) {
        			System.out.println(comment.toString());
        		}
        	}
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Weibo getWeibo(boolean isOauth,String ... args) {
		Weibo weibo = new Weibo();
		if(isOauth) {//oauth验证方式 args[0]:访问的token；args[1]:访问的密匙
			//weibo.setToken(args[0], args[1]);
			weibo.setToken("ea9751fb23b109b59ff415df99f611a8", "b71b4df1dc85c336c15fc79330ecad0b");
		}else {//用户登录方式
    		weibo.setUserId(args[0]);//用户名/ID
    		weibo.setPassword(args[1]);//密码
		}
		return weibo;
	}

}
