/**
 *
 */
package weibo4j.examples.statuses;

import java.util.List;

import weibo4j.Paging;
import weibo4j.Status;
import weibo4j.Weibo;

/**
 * @author hezhou
 *
 */
public class GetStatus {

	/**
	 * 获取单条ID的微博信息，作者信息将同时返回
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
    	System.setProperty("weibo4j.oauth.consumerSecret", Weibo.CONSUMER_SECRET);
        try {
        	Weibo weibo = getWeibo(true,args);
        	List<Status> list = weibo.getUserTimeline("1748033497", new Paging(1).count(4));
        	if(list.size() > 0) {
        		Status status = weibo.showStatus(list.get(0).getId());
            	System.out.println( status.getId() + "  : "+status.getText());
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Weibo getWeibo(boolean isOauth,String ... args) {
		Weibo weibo = new Weibo();
		if(isOauth) {//oauth验证方式 args[0]:访问的token；args[1]:访问的密匙
			//weibo.setToken(args[0], args[1]);
			//weibo.setToken("009f1b4f36d8340021431070f4a2f4f3", "689bbc7123ce4b27a3336a2993c7fcff");
			//weibo.setToken("f2b83c508b5f8ea3f859e31178ae264a", "2b1a3cf0c3998b7ccd81bef46f031507");
			//weibo.setToken("95905141d9dc7ca9beb87199fb295118", "4489372238ec36cbc111d252eb3dc484");
			weibo.setToken("ea9751fb23b109b59ff415df99f611a8", "b71b4df1dc85c336c15fc79330ecad0b");
		}else {//用户登录方式
    		weibo.setUserId("245110499");//用户名/ID
    		weibo.setPassword("abc123");//密码
		}
		return weibo;
	}

}
