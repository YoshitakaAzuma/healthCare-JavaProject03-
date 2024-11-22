package com.example.healthcare2.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.healthcare2.entity.Evaluation;
import com.example.healthcare2.entity.HealthData;
import com.example.healthcare2.service.EvaluationService;
import com.example.healthcare2.service.GptService;
import com.example.healthcare2.service.HealthDataService;
import com.example.healthcare2.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GptServiceImpl implements GptService{
	
	// APIキーを環境変数または設定ファイルから取得します
    @Value("${openai.api.key1}")
    private String apiKey1;
    @Value("${openai.api.key2}")
    private String apiKey2;
	
	private final HealthDataService healthDataService;
	private final UserService userService;
	private final EvaluationService evaluationService;
	// OpenAIのAPIエンドポイントURL
    private final String API_URL = "https://api.openai.com/v1/chat/completions";
    private final RestTemplate restTemplate;

	@Override //全体の評価、各項目の評価をしてデータベースに保存する
	public void evaluationAndRecord(int userId) {
		
		List<HealthData> dataList = healthDataService.getHealthDataListByUserId(userId);
		String[] data = new String[4]; 
		
		for (HealthData d: dataList) {
			// 全体
			data[0] += d.toString();
			// 体重と体脂肪率
			data[1] += d.getRecord_date() + ":体重" + d.getWeight() + "kg 体脂肪率" + d.getBody_fat_percentage() + "%;";
			// 血圧
			data[2] += d.getRecord_date() + ":最高血圧" + d.getSBP() + "最低血圧" + d.getDBP() + ";";
			// 歩数
			data[3] += d.getRecord_date() + ":歩数" + d.getSteps();
		}
		
		String[] message = new String[4];
		
		String userName = userService.getCurrentUserName();
		
		message[0] = "性別:" + userService.getGenderByUsername(userName) + "年齢:" + userService.getAgeByUsername(userName)
		+ "の人に下記のデータを全て参照して、次の条件に沿って総合的な健康面のアドバイスと、データの推移をフィールドバックしてください。"
		+ "条件１：アドバイスは非常に端的かつ年齢性別身長を考慮したもの"
		+ "条件２：アドバイスとフィールドバックは医者のような会話文"
		+ "条件３：”こんにちは”などの前置きは絶対につけずにいきなり本題から始める"
		+ "条件５：同年代の健康データと比較する"
		+ "条件５：「」は付けない"
		+ "/以下健康データ→" + data[0];
		
		message[1] = "性別:" + userService.getGenderByUsername(userName) + "年齢:" + userService.getAgeByUsername(userName) + "身長:" + healthDataService.getHealthDataByUserId(userId).getHeight()
						+ "の人に下記のデータを全て参照して、次の条件に沿って体重と体脂肪率についてのアドバイスと、データの推移をフィールドバックしてください。"
						+ "条件１：アドバイスは非常に端的かつ年齢性別身長を考慮したもの"
						+ "条件２：アドバイスとフィールドバックは医者のような会話文"
						+ "条件３：”こんにちは”などの前置きは絶対につけずにいきなり本題から始める"
						+ "条件５：同年代の健康データと比較する"
						+ "条件５：「」は付けない"
						+ "/以下体重と体脂肪率のデータ→" + data[1];
		
		message[2] =  "性別:" + userService.getGenderByUsername(userName) + "年齢:" + userService.getAgeByUsername(userName)
						+ "の人に下記のデータを全て参照して、次の条件に沿って血圧に関してのアドバイスと、データの推移をフィールドバックしてください。"
						+ "条件１：アドバイスは非常に端的かつ年齢性別身長を考慮したもの"
						+ "条件２：アドバイスとフィールドバックは医者のような会話文"
						+ "条件３：”こんにちは”などの前置きは絶対につけずにいきなり本題から始める"
						+ "条件５：同年代の健康データと比較する"
						+ "条件５：「」は付けない"
						+ "/以下血圧のデータ→" + data[2];
		
		message[3] =  "性別:" + userService.getGenderByUsername(userName) + "年齢:" + userService.getAgeByUsername(userName)
						+ "の人に下記のデータを全て参照して、次の条件に沿って歩数に関してのアドバイスと、データの推移をフィールドバックしてください。"
						+ "条件１：アドバイスは非常に端的かつ年齢性別身長を考慮したもの"
						+ "条件２：アドバイスとフィールドバックは医者のような会話文"
						+ "条件３：”こんにちは”などの前置きは絶対につけずにいきなり本題から始める"
						+ "条件５：同年代の健康データと比較する"
						+ "条件５：「」は付けない"
						+ "/以下歩数のデータ→" + data[3];
		
		
		
		HttpHeaders headers1 = new HttpHeaders();
		headers1.set("Authorization", "Bearer " + apiKey1); // APIキーをヘッダーに設定
		headers1.set("Content-Type", "application/json"); // リクエストのコンテンツタイプを設定
		HttpHeaders headers2 = new HttpHeaders();
		headers2.set("Authorization", "Bearer " + apiKey2); // APIキーをヘッダーに設定
		headers2.set("Content-Type", "application/json"); // リクエストのコンテンツタイプを設定
		
		// APIリクエストのボディを作成
		String[] body = new String[4];
		body[0] = "{\"model\":\"gpt-4\",\"messages\":[{\"role\":\"user\",\"content\":\"" + message[0] + "\"}]}";
		body[1] = "{\"model\":\"gpt-4\",\"messages\":[{\"role\":\"user\",\"content\":\"" + message[1] + "\"}]}";
		body[2] = "{\"model\":\"gpt-4\",\"messages\":[{\"role\":\"user\",\"content\":\"" + message[2] + "\"}]}";
		body[3] = "{\"model\":\"gpt-4\",\"messages\":[{\"role\":\"user\",\"content\":\"" + message[3] + "\"}]}";
        
        String[] evaluations = new String[4];
        
        //たぶんこっからfor
        for (int i = 0; i< 2; i++) {
        	int p = i + 2;
        	HttpEntity<String> request1 = new HttpEntity<>(body[i], headers1);
        	HttpEntity<String> request2 = new HttpEntity<>(body[p], headers2);

            // APIエンドポイントにPOSTリクエストを送信
            ResponseEntity<String> response1 = restTemplate.exchange(API_URL, HttpMethod.POST, request1, String.class);
            ResponseEntity<String> response2 = restTemplate.exchange(API_URL, HttpMethod.POST, request2, String.class);

            try {
            	ObjectMapper mapper = new ObjectMapper();
            	JsonNode root1 = mapper.readTree(response1.getBody());
            	JsonNode root2 = mapper.readTree(response2.getBody());
            	// APIレスポンスからメッセージコンテンツを抽出
            	evaluations[i] = root1.path("choices").get(0).path("message").path("content").asText();
            	evaluations[p] = root2.path("choices").get(0).path("message").path("content").asText();
            } catch (Exception e) {
            	e.printStackTrace();
            }
			/*try {
				Thread.sleep(20000);
			}catch (InterruptedException e) {
				
			}*/
        }
        Evaluation evaluation = new Evaluation(userId,LocalDate.now(),evaluations[0],evaluations[1],evaluations[2],evaluations[3]);
        if(evaluationService.findEvaluationsByUserId(userId) != null) {
        	//データが存在すれば
        	evaluationService.updateEvaluation(evaluation);
        }else {
        	// データが存在しなければ
        	evaluationService.insertEvaluation(evaluation);
        }
        
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
