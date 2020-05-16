package com.fusibang.help;

public class AlipayConfig {
    // 商户appid
    public static String APPID = "2021001139699753";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCHW1CWOh6Qc4JGzKyfieFkaBW3/CodmxgZIPGb16IbAHeBq8ehtPzRnCh0GOlLcdQySJ8BWrxcX+c8HnbCBdMsYvzwVzJzQsaFpZcx6RPmP7YkIQ4C68W0j1ASInSGf4xxxDJP4o3+kqf19W6Ucci562ZSU0QItufrsLEjazO+JX1J/y2hQ6d+P5rhAmHrAn4dQGPraRtW/Z4sB18hsf4GbTRoM6ylL/N4mzAe5uVbup0c1e4qCccTrKyDSOSXymj7K7Vm10IzxtmSwGe95iGpxjn6myWv1BiY7OIiJBYio6NGPTmRhCX6u6wcJNdAOkfpGTxlVLhahdnlHWAKQlVRAgMBAAECggEAEJ6uu3Nxc00rjuPswj4c1cZN/GRTC4WSpOs9n2QPvH8HLX3ZKkrdzCL/eJnic2gLpQzzsSfh/vmrm98lt9cdQdL5uc0LHcnyNFDLys4Sqm/iPndskAFBKlV6gG+InUcmCIYNo29u/fYl93ZmWIXMHr//tVYSAXvr6C2u4ml0VgF3hXWm41qfida9g9JRjYMIiGxAgUFI2YctFCDZAu3wCBK9Gi7VUVPaRDflClHmYu5tYQ8MF/vJ4aRPVAFqTgFFP0u1sKxWHWEvc3MPVXTjXutWcPyh7xL5WaAY3F9DolVI8r5LEcGEAnai076FgTPH0TCBhV4nzMdqAwPold/skQKBgQDpqflVnk45lVQGf6AbHDluhivfVxaGXjG1DS+yyssHra1SKY1U+11H2OdliH1kibqspWR7u+Me9Ir8DWshjcrt8lM9eB8BQtSeIK5swR93K6C7boNRvGfu9tlNdbMnCnT0UmiunhEjnRTz2NyOXuxAGDqG36+YScR97bw33cKx5QKBgQCUS6YjkUY0ymcRGghMGBiPdtw+KS7J8lZv922q4hYMAtRly1x03NiBT2ai6OfHWvYJGzQTHgR+ZqQwBVn7bii78nNVyOzIqjdV7PrTzx3/mVL5v8FUpOkkjkZGfm94aaBf3Tw6RXoD/SxjoLz3mz5ITVABNZ0SOpUtcSF61MYO/QKBgEeMyNCy3Pk9BLHIutoSVnPFmgGVxUv34/iwxP5Wor0DLaOPHbv8DKBHhvPMS7lRg1wCeTZ6Xh3IfBaadUedB5q9LUgRVE0qjbiuATtEF8KCFYHPYV7R9nFyHTBe7jDyPsXhxjRSKqVhIF1a9o6R+n3V7XYHYNmdHRICjjP9L/URAoGAVi9A9xrO+o9HJyFCATdfzsD9bwLwNl2o7pmWGuIafhCG8EIoEaLoEP7cX4GMcsfihZhRw2qGog91DJOPoBh9g/18GjcTpeuHDWEUxVRx0sKA/rT63v6YoK4vGXkRv6Fjm1MwVY1bREY3Lg+UwzJuGAcWoB1Qk++IE0jUdFaEZOECgYAg8IOJUZdVkfFhskU6Nqkxhxk50FznajF+lmq8QNGUED5sZDYeGe9HpBuJ0s/TjrRNtgsOaKUINTV9C4icFIqzQdj1CJM0J3+J5QcA99b3wiF0vTomRAFv4okMzVgV0NVsjsNLsdHXltlSgTfr7y1w9JTyuaPSJ5C5oqonhlyXuQ==";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://www.oute100.com/test/notify_url";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://www.oute100.com/test/return_url";
    // 请求网关地址
    public static String URL = "https://openapi.alipay.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh1tQljoekHOCRsysn4nhZGgVt/wqHZsYGSDxm9eiGwB3gavHobT80ZwodBjpS3HUMkifAVq8XF/nPB52wgXTLGL88Fcyc0LGhaWXMekT5j+2JCEOAuvFtI9QEiJ0hn+MccQyT+KN/pKn9fVulHHIuetmUlNECLbn67CxI2szviV9Sf8toUOnfj+a4QJh6wJ+HUBj62kbVv2eLAdfIbH+Bm00aDOspS/zeJswHublW7qdHNXuKgnHE6ysg0jkl8po+yu1ZtdCM8bZksBnveYhqcY5+pslr9QYmOziIiQWIqOjRj05kYQl+rusHCTXQDpH6Rk8ZVS4WoXZ5R1gCkJVUQIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";
}
