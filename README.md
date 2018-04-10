# 动态代理

## 使用场景

### 4.1 J2EE Web 开发中 Spring 的 AOP(面向切面编程) 特性
> 作用：目标函数之间解耦。
> 比如在 Dao 中，每次数据库操作都需要开启事务，而且在操作的时候需要关注权限。一般写法是在 Dao 的每个函数中添加相应逻辑，造成代码冗余，耦合度高。
> 使用动态代理前伪代码如下：

```
Dao {
    insert() {
        判断是否有保存的权限；
        开启事务；
        插入；
        提交事务；
    }

    delete() {
        判断是否有删除的权限；
        开启事务；
        删除；
        提交事务；
    }
}

使用动态代理的伪代码如下：

// 使用动态代理，组合每个切面的函数，而每个切面只需要关注自己的逻辑就行，达到减少代码，松耦合的效果
invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
    判断是否有权限；
    开启事务；
    Object ob = method.invoke(dao, args)；
    提交事务；
    return ob; 
}
```
### 4.2 基于 REST 的 Android 端网络请求框架 Retrofit

> 作用：简化网络请求操作。
> 一般情况下每个网络请求我们都需要调用一次HttpURLConnection或者HttpClient进行请求，或者像 Volley 一样丢进等待队列中，Retrofit 极大程度简化了这些操作，示例代码如下：

```
public interface GitHubService {
  @GET("/users/{user}/repos")
  List<Repo> listRepos(@Path("user") String user);
}

RestAdapter restAdapter = new RestAdapter.Builder()
    .setEndpoint("https://api.github.com")
    .build();

GitHubService service = restAdapter.create(GitHubService.class);
以后我们只需要直接调用

List<Repo> repos = service.listRepos("shanhouwang");
```


