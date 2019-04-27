#### springboot-jwt-demo
* springboot 2.1.4
* spring security
* jjwt
* mysql
* jpa
* redis


**token请求URL** 
- ` /auth`
  
**请求方式：**
- POST 

**参数：** 

|参数名|必选|类型|说明|
|:----    |:---|:----- |-----   |
|username |是  |string |用户名   |
|password |是  |string | 密码    |

 **返回示例**

``` 
  {
      "result": "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJkYXlicmVhayIsInN1YiI6ImFkbWluIiwiaWF0IjoxNTU2MzMxMDYyLCJleHAiOjE1NTYzNzQyNjJ9.ofVHF2oB43C943QU4ertwWnjYJq78vtZKN7thiAykic_ex1Y2buUW-qkcH_qJa7W7W-TImbp499jpf_x8RGohg"
  }
```

 **返回参数说明** 

|参数名|类型|说明|
|:-----  |:-----|-----                           |
|result |string|token |

### TODO
* 刷新token
* 登出
