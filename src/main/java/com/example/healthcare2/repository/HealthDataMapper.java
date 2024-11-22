package com.example.healthcare2.repository;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.healthcare2.entity.HealthData;

@Mapper
public interface HealthDataMapper {
	
	// ある日以降ののユーザデータをリスト形式で取得
	List<HealthData> findDataListByUserId(@Param("user_id")int userId,@Param("point_date") LocalDate point_date);
	// ユーザの最初のデータの日付を取得
	LocalDate findFirstDateByUserId(int userId);
	// 最新のユーザデータを取得
	HealthData findLatestDataByUserId(int userId);
	// ユーザデータを登録
	void insertHealthData(HealthData data);
	
}
