[TOC]

# api 合集



## 1. 测试

- 接口：`/api/temp/{data}`
- 请求方式：GET
- 示例请求：`/api/temp/123321`
- 返回示例

```json
{
    "code": 200,
    "msg": "操作成功",
    "data": "12345654321"
}
```





## 2. 刷步

- 接口：`/api/mi/{phone}/{password}/{stepNum}`
- 请求方式：GET
- 示例请求：`/api/mi/18338413841/My123321/20000`

```json
{
    "code": 200,
    "success": true,
    "message": "请求成功",
    "data": {
        "tip": "登录失败"
    }
}
```

- 示例请求：`/api/mi/16698739873/Me123321/66666`

```json
{
    "code": 200,
    "success": true,
    "message": "请求成功",
    "data": {
        "tip": "每日修改步数：66666 success！"
    }
}
```

