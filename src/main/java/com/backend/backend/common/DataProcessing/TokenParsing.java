package com.backend.backend.common.DataProcessing;

import org.json.JSONObject;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

public class TokenParsing {
    private HttpServletRequest request;
        public String ExtractNickname(HttpServletRequest request)
        {
        String header=request.getHeader("Authorization"); // Authorization:key값 형태로 되어 있음
        String payload=header.split("\\.")[1]; // payload 부분만 발췌
        Base64.Decoder decoder=Base64.getUrlDecoder();
        JSONObject jObject = new JSONObject(new String(decoder.decode(payload))); //decode된 payload부분을 jobject에 넣음
        String nick = jObject.getString("nickname"); // key값이 nickname인 부분만 발췌해서 nick에 저장하고 return 함
        return nick;
        }
}

