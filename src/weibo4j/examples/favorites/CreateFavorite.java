/**
 * 
 */
package weibo4j.examples.favorites;

import weibo4j.Status;
import weibo4j.Weibo;
import weibo4j.WeiboException;

/**
 * @author hezhou
 *
 */
public class CreateFavorite {

	/**
	 * 添加收藏
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
    	System.setProperty("weibo4j.oauth.consumerSecret", Weibo.CONSUMER_SECRET);
		try {
			Status status = getWeibo(true,args).createFavorite(Long.parseLong(args[2]));
			System.out.println(status.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}
	
	private static Weibo getWeibo(boolean isOauth,String[] args) {
		Weibo weibo = new Weibo();
		if(isOauth) {//oauth验证方式 args[0]:访问的token；args[1]:访问的密匙
			weibo.setToken(args[0], args[1]);
		}else {//用户登录方式
    		weibo.setUserId(args[0]);//用户名/ID
    		weibo.setPassword(args[1]);//密码
		}
		return weibo;
	}

}
