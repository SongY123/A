package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import javafx.util.Pair;
import net.sf.json.JSONObject;

public class test{
public static void main(String[]args){
	String a = "凤凰军 评 ： 沙欣 3 试射  中国 可借 巴 铁 验证 DF 21 反 航母";
	String[]result = a.split("\\s+");
	String []result2 = a.split("\\s");
	for (int i = 0;i<result.length;i++)
		System.out.println(result[i]);
	for (int i = 0;i<result2.length;i++)
		System.out.println(result2[i]);
	System.out.println(result.length);
	System.out.println(result2.length);
}
}