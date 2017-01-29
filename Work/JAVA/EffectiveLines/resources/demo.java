package edu.zhangzl.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.zhangzl.entity.DriectionVO;
import edu.zhangzl.entity.TypeVO;
import edu.zhangzl.serves.DireServes;
import edu.zhangzl.tools.BasicAction;

public class DireAction extends BasicAction {
	private List<String> list;
	private List<Integer> list_dire;
	private DireServes direServes;
	private List<DriectionVO> list_dri;
	private int did;
	private int tid;
	private DriectionVO drieVo;
	private List<TypeVO> list_type;
	private List<String> list_type_dire;
	private String type_ids;
	private int page;
	private int pageSize;
	
	
	
	public List<Integer> getList_dire() {
		return list_dire;
	}
	public void setList_dire(List<Integer> list_dire) {
		this.list_dire = list_dire;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getType_ids() {
		return type_ids;
	}
	public void setType_ids(String type_ids) {
		this.type_ids = type_ids;
	}
	public List<String> getList_type_dire() {
		return list_type_dire;
	}
	public void setList_type_dire(List<String> list_type_dire) {
		this.list_type_dire = list_type_dire;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public List<TypeVO> getList_type() {
		return list_type;
	}
	public void setList_type(List<TypeVO> list_type) {
		this.list_type = list_type;
	}
	public DriectionVO getDrieVo() {
		return drieVo;
	}
	public void setDrieVo(DriectionVO drieVo) {
		this.drieVo = drieVo;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public List<DriectionVO> getList_dri() {
		return list_dri;
	}
	public void setList_dri(List<DriectionVO> list_dri) {
		this.list_dri = list_dri;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public DireServes getDireServes() {
		return direServes;
	}
	public void setDireServes(DireServes direServes) {
		this.direServes = direServes;
	}
	/**
	 * 非异步方式添加方向
	 * @return
	 */
	public String add_dire(){
		direServes.add_dire(list);
		return "dire_list"; 
	}
	
	
	public String showDire_all(){
		list_dri = direServes.showDire();
		responseMsg(list_dri);
		return null;
	}
	
	public String showDire_sec(){
		return "dire_list";
	}
	
	/**
	 * 显示课程方向信息
	 * @return
	 */
	public String showDire(){
		list_dri = direServes.showDire(page,pageSize);
		int totalPage=direServes.selPage();
		if(totalPage%pageSize==0){
			totalPage=totalPage/pageSize;
		}else{
			totalPage=(totalPage/pageSize)+1;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("list_dri", list_dri);
		map.put("totalPage", totalPage);
		responseMsg(map);
		return null;
	}
	
	/**
	 * 通过Id查找课程方向
	 * @return
	 */
	public String showDetile(){

		drieVo = direServes.showDetile(did);
		//查找课程对应的详细方向
		list_type = direServes.showType(did);
		return "show_type";
		
	}
	
	
	/**
	 * 通过方向tid删除单个课程类型
	 * @return
	 */
	public String delType(){
		direServes.delType(tid);
		 return this.showDetile();
	}
	/**
	 * 通过异步方式删除方向信息
	 * @return
	 */
	public String delType_sec(){
		direServes.delType(tid);
		responseMsg("1");
		return null;
	}
	
	/**
	 * 添加类型信息
	 * @return
	 */
	public String add_type(){
		direServes.add_type(list_type_dire,did);
		list_type=direServes.showType(did);
		return "show_type";
	}
	/**
	 * 
	 * 通过进行点击复选框后，一键删除多个信息
	 * @return
	 */
	public String del_typeByCheck(){
		direServes.del_typeByCheck(type_ids);
		list_type=direServes.showType(did);
		return "show_type";
	}
	
	
	/**
	 * 传递课程中用于显示在下拉菜单中的信息
	 * @return
	 */
	public void show_kc_info(){
		list_dri = direServes.showDire();
		responseMsg(list_dri);
	}
	
	/**
	 * 用于显示在添加课程信息页面中的下拉列表中的类型信息
	 *@return
	 */
	
	public void show_type_info(){
		list_type = direServes.showType(did);
		responseMsg(list_type);
	}
	
	
	public String show_type_all(){
		list_type = direServes.show_type_all();
		responseMsg(list_type);
		return null;
	}
	/**
	 * 通过Id删除方向信息
	 * @return
	 */
	public void delDriById(){
		direServes.delDriById(did);
		direServes.delTypeById(did);
	}
	
	/**
	 * 显示所有类型信息
	 * @return
	 */
	public String showType_all(){
		List<TypeVO> list_Type_all = direServes.showType_all(page,pageSize);
		responseMsg(list_Type_all);
		return null;
	}
	
	/**
	 * 在数据库中添加方向信息（包括课程类型的名称和其方向号）
	 * @return
	 */
	public String add_type_dire(){
		direServes.add_type_dire(list,list_dire);
		return "show_type_list";
	} 
}
