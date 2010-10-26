/**
 * 
 */
package weibo4j.examples.ids;

import weibo4j.Weibo;
import weibo4j.WeiboException;

/**
 * @author hezhou
 *
 */
public class GetFollowersIDs {

	/**
	 * 获取用户粉丝对象uid列表 
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
    	System.setProperty("weibo4j.oauth.consumerSecret", Weibo.CONSUMER_SECRET);
		try {
			//args[2]:关注用户的id
			int[] ids = getWeibo(true,args).getFollowersIDs(1748033497).getIDs();
			for(int id : ids) {
				System.out.println(id);
			}
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}
	
	private static Weibo getWeibo(boolean isOauth,String[] args) {
		Weibo weibo = new Weibo();
		if(isOauth) {//oauth验证方式 args[0]:访问的token；args[1]:访问的密匙
			//weibo.setToken(args[0], args[1]);
			weibo.setToken("ea9751fb23b109b59ff415df99f611a8", "b71b4df1dc85c336c15fc79330ecad0b");
		}else {//用户登录方式
//    		weibo.setUserId(args[0]);//用户名/ID
//    		weibo.setPassword(args[1]);//密码
			weibo.setUserId("hezhou414@126.com");
    		weibo.setPassword("hezhou88");
		}
		return weibo;
	}

}
