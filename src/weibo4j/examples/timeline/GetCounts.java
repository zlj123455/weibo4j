/**
 * 
 */
package weibo4j.examples.timeline;

import java.util.List;

import weibo4j.Count;
import weibo4j.Status;
import weibo4j.Weibo;

/**
 * @author hezhou
 *
 */
public class GetCounts {

	/**
	 * 批量统计微博的评论数，转发数
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
    	System.setProperty("weibo4j.oauth.consumerSecret", Weibo.CONSUMER_SECRET);
        try {
        	Weibo weibo = getWeibo(true,args);
        	List<Status> statuses = weibo.getUserTimeline("1748033497");
        	StringBuilder ids = new StringBuilder();
        	for(Status status : statuses) {
        		ids.append(status.getId()).append(',');
        	}
        	ids.deleteCharAt(ids.length() - 1);
        	List<Count> counts = weibo.getCounts(ids.toString());
    		for(Count count : counts) {
    			System.out.println(count.toString());
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
