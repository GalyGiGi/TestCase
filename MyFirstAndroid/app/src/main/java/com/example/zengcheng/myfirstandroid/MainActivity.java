package com.example.zengcheng.myfirstandroid;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.ByteBuffer;
import java.util.AbstractList;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private TextView mText;
    private EditText mET;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = (TextView) findViewById(R.id.text);
        mET = (EditText) findViewById(R.id.edit_text);

//        testGetString();
    }

    private void changeArray(int[] a) {
        a[0]=7;
    }
    private void changeString(String a){
        String temp = "changed";
    }
    private void testJsonArray(String msg) {

        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(msg);
            String result = jsonArray.toString();
            Log.i(TAG, "jsonArray转换后result:" + result);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void testGetString() {
        String s = getApplicationContext().getString(R.string.pref_key_enable_background_play);
        ByteBuffer bf;
        Set set;
        AbstractSet as;
        AbstractList al;
        mText.setText(s);
    }

    private void testFeimiJson() {

    }

    private void testAdd() {
        List<Object> list = new ArrayList<>();
        list.add("");
        List<Number> list2 = new ArrayList<>();
        Number n = null;
        list2.add(n);
        list2.add(new Integer(2));
        Set<Number> numbers;
        Set<Integer> integers = null;
        Set<Double> doubles = null;
//        numbers = union(integers, doubles);//编译不过
        Comparable<Number> comparableNum = new Comparable<Number>() {
            @Override
            public int compareTo(@NonNull Number o) {
                return 0;
            }
        };
        Comparable<Integer> comparableInt = new Comparable<Integer>() {
            @Override
            public int compareTo(@NonNull Integer o) {
                return 0;
            }
        };
        List<? extends CompareStudent> list3 = null;
        CompareStudent student = max(list3);
        swap2(new ArrayList<Integer>(), 1, 2);
        HashSet<Integer> set = new HashSet<>();
    }

    public Map<String, Object> parse(String result) throws JSONException {
        JSONObject response = new JSONObject(result);
        Map<String, Object> map = new HashMap<String, Object>();
        JSONArray resultObj = response.optJSONArray("result");
        for (int i = 0; i < resultObj.length(); i++) {
            JSONObject jsonObject = resultObj.getJSONObject(i);
            Object key = jsonObject.opt("key");
            if (key.equals(JSONObject.NULL)) {//神坑
                continue;
            }
            map.put((String) key, jsonObject.toString());

//            if (key != null && !((String) key).equals("null")) {
//                map.put((String) key, jsonObject.toString());
//            }
        }
        return map;
    }

    class Student implements Comparable<Student> {

        @Override
        public int compareTo(@NonNull Student o) {
            return 0;
        }
    }

    class CompareStudent extends Student {
    }

    private <E> E add(E e) {
        return e;
    }

    private <E> Set<E> union(Set<? extends E> e1, Set<? extends E> e2) {
        return null;
    }

    private void reduce(List<?> list) {
    }

    private <T extends Comparable<? super T>> T max(List<? extends T> list) {
        T max = list.get(0);
        for (T t : list) {
            if (max.compareTo(t) < 0) {
                max = t;
            }
        }
        return max;
    }

    public void swap(List<?> list, int i, int j) {
//        list.set(i, list.set(j, list.get(i)));//编译不过
        swapHelper(list, i, j);//需要借助辅助类，但是这样对于客户端更简单
    }

    public <E> void swap2(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }

    private <E> void swapHelper(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));

    }

    private <T> void putFavarite(Class<T> type, T instance) {

    }

    public int[] packDataForConfigV5(String source, String model, int start, int max, int maxLength,
                                     JSONArray jsonArray) throws JSONException {
        String data = source.toString();
        int dataLength = data.length();
        int count = dataLength / maxLength;
        if (count != 0 && dataLength % maxLength != 0) {
            count++;
        }
        if (count + 1 > max) {
            return new int[0];
        }
        int[] keyUsed = new int[count + 1];
        int index = 0;
        if (count > 0) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("key", start);
            jsonObject.put("model", model);
            jsonObject.put("ts", start + 1);
            jsonObject.put("tc", count);
            keyUsed[index] = start;
            start++;
            index++;
        } else {
            count = 1;
        }
        int dataStart = 0;
        while (count > 0) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("key", start);
            jsonObject.put("model", model);
            if (count == 1) {
                jsonObject.put("data", data.substring(dataStart));
            } else {
                jsonObject.put("data", data.substring(dataStart, dataStart + maxLength));
            }
            jsonArray.put(jsonObject);
            keyUsed[index] = start;
            start++;
            count--;
            index++;
            dataStart += maxLength;
        }
        return keyUsed;
    }

    private void testHuamiJson() {
        String result = "{\"message\":\"ok\",\"result\":[{\"data\":{\"data\":\"i am test data from zc\"},\"uid\":\"923522198\",\"component_id\":\"10117\",\"update_time\":\"1492657366\",\"key\":\"3\"},{\"data\":{\"data\":\"i am test data4 from zc\"},\"uid\":\"923522198\",\"component_id\":\"10117\",\"update_time\":\"1492659002\",\"key\":\"4\"}],\"code\":0}";
        try {
            JSONObject response = new JSONObject(result);
            Map<String, Object> map = new HashMap<String, Object>();

            JSONArray resultObj = response.optJSONArray("result");
            for (int i = 0; i < resultObj.length(); i++) {
                JSONObject jsonObject = resultObj.getJSONObject(i);
                String key = (String) jsonObject.get("key");
                map.put(key, jsonObject);
            }
            map.entrySet();
//            JSONObject jsonObject = resultObj.getJSONObject(0);
//            if (jsonObject != null) {
//                Iterator<String> iterator = jsonObject.keys();
//                while (iterator.hasNext()) {
//                    String key = iterator.next();
//                    map.put(key, jsonObject.get(key));
//                }
//            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void jump(View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivityForResult(intent, 3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "--onActivityResult---");
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 点击按钮触发的测试方法
     *
     * @param v
     */
    public void testCase(View v) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put("123456");
        String jstr = jsonArray.toString();
        testJsonArray(jstr);
        int[] a = new int[]{1, 2, 3};
        changeArray(a);
        Log.i(TAG, "a[0]=" + a[0]);
        jsonArray = new JSONArray();
//        jsonArray.put("123456");

//        try {
//            parse("{\"message\":\"ok\",\"result\":[{\"uid\":null,\"data\":\"\",\"component_id\":null,\"update_time\":null,\"key\":null},{\"uid\":\"923522198\",\"data\":{\"userV1\":\"i am test data from zc\"},\"component_id\":\"10012\",\"update_time\":\"1495000344\",\"key\":\"4\"}],\"code\":0}");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        String content = mET.getText().toString();
//        try {
//            packDataForConfigV5(content, "xiao.mi.demo", 100, 1000, 5, new JSONArray());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    public void testJson(View view) {
        String jString = new String("{\"msg_id\":7,\"type\":\"vf_stop\"}{\"msg_id\":7,\"type\":\"record_capture\"}{\"msg_id\":7,\"type\":\"vf_start\"}");
//        parseMsg(jString);
        try {
            JSONObject jsonObject = new JSONObject(jString);
            String s = jsonObject.toString();
            JSONArray j = jsonObject.names();
            Iterator a = jsonObject.keys();
            int size = jsonObject.length();
//            if(jString.c){
//
//            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i(TAG, "json异常：" + e.getMessage());
        }
        try {
            JSONArray jsonArray = new JSONArray(jString);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i(TAG, "json异常：" + e.getMessage());
        }
        Gson gson = new Gson();
        String result = gson.toJson(jString);
        JSONObject[] jsonArray = gson.fromJson(jString, JSONObject[].class);
//        gson.fromJson()

    }

    /**
     * 解决并包问题，把多个json字符串拆成多个
     *
     * @param s
     * @return
     */
    private List<String> parseMsg(String s) {
        ArrayList<String> list = new ArrayList<String>();

        if (s.contains("[")) {
            list.add(s);
            return list;
        }
        int indexEnd = -1;
        while ((indexEnd = s.indexOf("}")) < s.length() - 1) {
            String temp = s.substring(0, indexEnd + 1);
            list.add(temp);
            s = s.substring(s.indexOf("{") + 1);
            s = s.substring(s.indexOf("{"));
        }
        list.add(s);


        return list;
    }

    class JsonMessage {
        @SerializedName("msg_id")
        public String msgId;
        @SerializedName("type")
        public String type;
        @SerializedName("rval")
        public String rval;
    }
}
