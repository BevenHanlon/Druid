package com.bodao.ehrms.DAO;

import java.util.List;

import com.bodao.ehrms.entity.Record;

/**
 * ���޼�¼
 * @author 59112
 *
 */
public interface RecordDAO {

	/**
	 * ����Ա��ѯ�������޼�¼
	 * @return
	 */
	public List<Record> findAll();
	/**
	 * �û���ѯ���˵����޼�¼
	 * @param recordeser_id
	 * @return
	 */
	public List<Record> findRecordByRecordUser_id(int user_id);
	
	/**
	 * �⳵
	 * @param user_id
	 * @param car_id
	 * @return
	 */
	public int addrecord (int user_id,int car_id);
	/**
	 * ���ݼ�¼id��ѯ�⳵��¼
	 * @param id
	 * @return
	 */
	public Record select (int id);
	/**
	 * ����
	 * @param user_id
	 * @param car_id
	 * @return
	 */
	public int updaterecord (double rent,int user_id,int car_id);

	/**
	 * �����û�id������id��ѯ���޼�¼
	 * @param user_id
	 * @param car_id
	 * @return
	 */
	public Record findRecord(int user_id,int car_id);
}
