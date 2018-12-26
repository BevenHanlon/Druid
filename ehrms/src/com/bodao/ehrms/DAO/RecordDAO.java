package com.bodao.ehrms.DAO;

import java.util.List;

import com.bodao.ehrms.entity.Record;

/**
 * 租赁记录
 * @author 59112
 *
 */
public interface RecordDAO {

	/**
	 * 管理员查询所有租赁记录
	 * @return
	 */
	public List<Record> findAll();
	/**
	 * 用户查询个人的租赁记录
	 * @param recordeser_id
	 * @return
	 */
	public List<Record> findRecordByRecordUser_id(int user_id);
	
	/**
	 * 租车
	 * @param user_id
	 * @param car_id
	 * @return
	 */
	public int addrecord (int user_id,int car_id);
	/**
	 * 根据记录id查询租车记录
	 * @param id
	 * @return
	 */
	public Record select (int id);
	/**
	 * 还车
	 * @param user_id
	 * @param car_id
	 * @return
	 */
	public int updaterecord (double rent,int user_id,int car_id);

	/**
	 * 根据用户id和汽车id查询租赁记录
	 * @param user_id
	 * @param car_id
	 * @return
	 */
	public Record findRecord(int user_id,int car_id);
}
