package com.sist.manager;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

@Component
public class NaverDataManager {

	public List<String> jsonParser(String json){
		
		//{....title:'����', items:[������ 100��...]} {}�� �����ϸ� Object�� �ް� []�� �����ϸ� Arraylist�� ���� 
		List<String> list = new ArrayList<String>();
		try {
			JSONParser jp=new JSONParser();
			JSONObject root=(JSONObject)jp.parse(json);
			JSONArray arr=(JSONArray)root.get("items");
			for(int i=0; i<arr.size();i++) {
				JSONObject obj=(JSONObject)arr.get(i);
				String desc=(String)obj.get("description");
				list.add(desc);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
    public String foodRecommand(String ss) {
        String clientId = "fTzO2IIdYFA_AbL9Y_HA"; //���ø����̼� Ŭ���̾�Ʈ ���̵�
        String clientSecret = "wkXAoabtEp"; //���ø����̼� Ŭ���̾�Ʈ ��ũ��


        String text = null;
        try {
            text = URLEncoder.encode(ss+" ������� ��õ", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("�˻��� ���ڵ� ����",e);
        }


        String apiURL = "https://openapi.naver.com/v1/search/blog?display=100&query=" + text;    // JSON ���
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // XML ���


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);


        System.out.println(responseBody);
        return responseBody;
    }


    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // ���� ȣ��
                return readBody(con.getInputStream());
            } else { // ���� �߻�
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API ��û�� ���� ����", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL�� �߸��Ǿ����ϴ�. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("������ �����߽��ϴ�. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API ������ �д� �� �����߽��ϴ�.", e);
        }
    }
}