# Garmin app data collector
This project is used to receive the tri-axes accelerometer data from my garmin app.
Garmin app 三轴加速度数据接收服务端代码。

## Usage
Embed the code below somewhere you want to upload your data.
Garmin app中嵌入这段代码并在需要使用的地方进行调用。
```monkeyc
function uploadAccel() {
    System.println("Executing upload Web Request");
    var params = {"x"=>mX,"y"=>mY,"z"=>mZ,"p"=>getProfileInfo()};
    var options = {
        :method => Communications.HTTP_REQUEST_METHOD_POST,
        :responseType => Communications.HTTP_RESPONSE_CONTENT_TYPE_JSON,
        :headers => {
            "Content-Type" => Communications.REQUEST_CONTENT_TYPE_JSON
        }
    };

    Communications.makeWebRequest(
        "http://x.x.x.x/upload",
        params,
        options,
        method(:onReceive)
    );
}

// Receive the data from the web request
function onReceive(responseCode, data) {
    if (responseCode == 200) {
        System.println("data:"+data);
        Attention.playTone(Attention.TONE_SUCCESS);
    } else {
        Attention.playTone(Attention.TONE_FAILURE);
    }
}
```