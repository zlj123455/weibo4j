## weibo4j-oauth2 ##

是一款基于新浪微博开放平台API（V2）接口的支持oauth2授权认证方式的Java SDK。第三方开发者可以通过这个SDK开发自己的application。

## 最新版本下载地址 ##
<br>Weibo4j-oauth2.0-beta2.1.1:</br>
<br><a href='http://code.google.com/p/weibo4j/downloads/detail?name=weibo4j-oauth2-beta2.1.1.zip'>http://code.google.com/p/weibo4j/downloads/detail?name=weibo4j-oauth2-beta2.1.1.zip</a></br>
## API文档 ##
<br>开发者可以参考新浪微博开放平台提供的api(V2)文档</br>
http://t.cn/aFFn0C
## 意见与反馈 ##
<br>如果对sdk有更好的意见或是想法以及bug提交都可以反馈到这里</br>
http://t.cn/aCS5Wx

## 使用方法 ##
1、
请先填写相关配置：在Config.properties里
client\_ID ：appkey                           创建应用获取到的appkey
client\_SERCRET ：app\_secret           创建应用获取到的appsecret
redirect\_URI : 回调地址                     OAuth2的回调地址

然后调用example里：OAuth4Code.java
```
public class OAuth4Code {
	public static void main(String [] args) throws WeiboException, IOException{
		Oauth oauth = new Oauth();
		BareBonesBrowserLaunch.openURL(oauth.authorize("code"));
		System.out.print("Hit enter when it's done.[Enter]:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String code = br.readLine();
		Log.logInfo("code: " + code);
		try{
			System.out.println(oauth.getAccessTokenByCode(code));
		} catch (WeiboException e) {
			if(401 == e.getStatusCode()){
				Log.logInfo("Unable to get the access token.");
			}else{
				e.printStackTrace();
			}
		}
	}

}
```
运行后会弹出浏览器地址跳转到授权认证页面，然后输入你的微博帐号和密码，会调转到你的回调地址页面，url后面会传递code参数

然后在console输入code就能获取到oauth2的accesstoken

接下来即可调用example，再此以user／show接口为例：
```
public class ShowUser {

	public static void main(String[] args) {
		String access_token = args[0];
		String uid =args[1];
		Users um = new Users();
		um.client.setToken(access_token);
		try {
			User user = um.showUserById(uid);
			Log.logInfo(user.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
```
weibo.setToken为auth4code获取到的oauth2的accesstoken。

由于目前只开放支持code的oauth认证方式，所以sdk暂时只支持code获取token方式。


# Weibo4J Weibo4Android #

是一款基于新浪微博开放平台API接口的Java SDK（同时支持android开发）。第三方开发者可以通过这个SDK开发自己的application。以后weibo4j和weibo4android会在此同步进行更新。

## 最新版本下载地址 ##
<br>Weibo4J:</br>
<br><a href='http://code.google.com/p/weibo4j/downloads/detail?name=weibo4j-1.2.1.zip'>http://code.google.com/p/weibo4j/downloads/detail?name=weibo4j-1.2.1.zip</a></br>
<br>Weibo4Android:</br><br><a href='http://code.google.com/p/weibo4j/downloads/detail?name=weibo4android-1.2.1.zip'>http://code.google.com/p/weibo4j/downloads/detail?name=weibo4android-1.2.1.zip</a><br>
<h2>API文档</h2>
<br>开发者可以参考新浪微博开放平台提供的api文档</br>
<a href='http://t.cn/hrTou2'>http://t.cn/hrTou2</a>

<h2>意见与反馈</h2>
<br>如果对sdk有更好的意见或是想法以及bug提交都可以反馈到这里</br>
<a href='http://t.cn/aCS5Wx'>http://t.cn/aCS5Wx</a>

<h2>简单的示例</h2>
oauth认证：<br>
<pre><code>           Weibo weibo = new Weibo();<br>
           RequestToken requestToken = weibo.getOAuthRequestToken();<br>
           AccessToken accessToken = null;<br>
           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));<br>
	   while (null == accessToken) {<br>
		 System.out.println("Open the following URL and grant access to your account:");<br>
	         System.out.println(requestToken.getAuthorizationURL());<br>
		 BareBonesBrowserLaunch.openURL(requestToken.getAuthorizationURL());<br>
		 System.out.print("Hit enter when it's done.[Enter]:");<br>
<br>
		 String pin = br.readLine();<br>
	         System.out.println("pin: " + br.toString());<br>
		 try{<br>
			accessToken = requestToken.getAccessToken(pin);<br>
		 } catch (WeiboException te) {<br>
			if(401 == te.getStatusCode()){<br>
			System.out.println("Unable to get the access token.");<br>
			}else{<br>
			te.printStackTrace();<br>
			}<br>
		 }<br>
		 }<br>
			System.out.println("Got access token.");<br>
			System.out.println("Access token: "+ accessToken.getToken());<br>
			System.out.println("Access token secret: "+ accessToken.getTokenSecret());<br>
</code></pre>
发布微博：<br>
<pre><code>             weibo.setToken(accessToken.getToken(), accessToken.getTokenSecret());<br>
             Status status = weibo.updateStatus("2057");<br>
             System.out.println("Successfully updated the status to ["<br>
					+ status.getText() + "].");<br>
</code></pre>
获取usertimeline：<br>
<pre><code>                        Weibo weibo = new Weibo();<br>
			weibo.setToken(args[0],args[1]);<br>
			//获取24小时内前20条用户的微博信息;args[2]:用户ID<br>
			List&lt;Status&gt; statuses = weibo.getUserTimeline(args[2],new Paging(1,20));<br>
			for (Status status : statuses) {<br>
	            System.out.println(status.getUser().getName() + ":" +status.getId()+":"+<br>
	                               status.getText() + status.getOriginal_pic());<br>
	        }<br>
</code></pre>