package com.study.zh.manager.service.Impl;

import com.study.zh.bean.TPermission;
import com.study.zh.bean.TRole;
import com.study.zh.bean.TUser;
import com.study.zh.exception.LoginFailException;
import com.study.zh.manager.dao.TUserMapper;
import com.study.zh.manager.service.UserService;
import com.study.zh.util.Const;
import com.study.zh.util.Page;
import com.study.zh.vo.Data;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TUserMapper userMapper;

    public TUser queryUserLogin(Map<String, Object> paramMap) {
        TUser user = userMapper.queryUserLogin(paramMap);
        if(user==null){
            throw new LoginFailException("账号或密码有误！！");
        }
        return user;
    }

    @Override
    public Page queryUserPage(Map<String, Object> paramMap) {

        Page page = new Page((Integer) paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));
        Integer startIndex = page.getStartIndex();
        paramMap.put("startIndex",startIndex);
        List<TUser> datas = userMapper.queryList(paramMap);

        page.setData(datas);

        Integer totalsize = userMapper.queryCount(paramMap);

        page.setTotalsize(totalsize);
        return page;
    }

    @Override
    public TUser getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateUser(TUser user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }


/*
    @Override
    public Page queryUserPage(Integer pageno, Integer pagesize) {
        Page page = new Page(pageno,pagesize);
        Integer startIndex = page.getStartIndex();
        List<TUser> datas = userMapper.queryList(startIndex,pagesize);

        page.setDatas(datas);

        Integer totalsize = userMapper.queryCount();

        page.setTotalsize(totalsize);
        return page;
    }
*/

    @Override
    public int saveUser(TUser user) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String createtime = df.format(date);
        user.setCreatetime(createtime);
        user.setUserpswd(Const.Const_Default_Password);
        return userMapper.insert(user);
    }

    @Override
    public int insertUser(TUser user) {
        return userMapper.insert(user);
    }


    /*    @Override
    public int deletBatchUser(Integer[] ids) {
        int totalCount = 0;
        for (Integer id:ids){
           int count = userMapper.deleteByPrimaryKey(id);
           totalCount += count;
        }
        if (totalCount != ids.length){
            throw new RuntimeException("批量删除异常");
        }
        return totalCount;
    }*/


	/*@Override
	public int deleteBatchUserByVO(Data data) {
		return userMapper.deleteBatchUserByVO(data);
	}*/



    public int deleteBatchUserByVO(Data data) {
        return userMapper.deleteBatchUserByVO(data.getDatas());
    }

    @Override
    public List<TUser> queryAllUser() {
        return userMapper.queryAllUser();
    }

    @Override
    public List<TRole> queryAllRole() {
        return userMapper.queryAllRole();
    }

    @Override
    public List<Integer> queryRoleByUserid(Integer id) {
        return userMapper.queryRoleByUserid(id);
    }

    @Override
    public void saveUserRoleRelationshop(Integer userid,Data data) {
        userMapper.saveUserRoleRelationshop(userid,data);
    }

    @Override
    public void deleteUserRoleRelationshop(Integer userid, Data data) {
        userMapper.deleteUserRoleRelationshop(userid,data);
    }

    @Override
    public void saveAllUserRoleRelationshop(Integer userid, Data data) {
        userMapper.saveAllUserRoleRelationshop(userid,data);
    }

    @Override
    public void deleteAllUserRoleRelationshop(Integer userid, Data data) {
        userMapper.deleteAllUserRoleRelationshop(userid,data);
    }

    @Override
    public List<TPermission> queryPermissionByUserid(Integer id) {
        return userMapper.queryPermissionByUserid(id);
    }

    @Override
    public int adduser(MultipartFile file) throws IOException {

        int result = 0;
        //存放excel表中所有user数据
        List<TUser> userList = new ArrayList<TUser>();

        //file.getOriginalFilename()方法 得到上传时的文件名
        String fileName = file.getOriginalFilename();
        //截取文件名的后缀
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);

        //file.getInputStream()方法  返回InputStream对象 读取文件的内容
        InputStream ins = file.getInputStream();

        Workbook wb = null;

        /*判断文件后缀
            XSSF － 提供读写Microsoft Excel OOXML XLSX格式档案的功能。
            HSSF － 提供读写Microsoft Excel XLS格式档案的功能。*/
        if(suffix.equals("xlsx")){
            wb = new XSSFWorkbook(ins);
        }else{
            wb = new HSSFWorkbook(ins);
        }

        //获取excel表单的sheet对象
        Sheet sheet = wb.getSheetAt(0);
        //如果sheet不为空，就开始遍历表中的数据
        if(null != sheet){
            //line = 1 :从表的第二行开始获取记录
            for(int line = 1; line <= sheet.getLastRowNum();line++){
                //excel表单的sheet的行对象
                Row row = sheet.getRow(line);

                //如果某行为空，跳出本行
                if(null == row){
                    continue;
                }
                //获取第一个单元格的内容
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                String loginAcct = row.getCell(0).getStringCellValue();

                //获取第二个单元格的内容
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                String  userName= row.getCell(1).getStringCellValue();

                //获取第三个单元格的内容
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                String userPswd = row.getCell(2).getStringCellValue();

                //获取第四个单元格的内容
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                String email = row.getCell(3).getStringCellValue();
                TUser user = new TUser();


                user.setLoginacct(loginAcct);
                user.setUsername(userName);
                user.setUserpswd(userPswd);
                user.setEmail(email);


                userList.add(user);

            }

            for(TUser user:userList){

                /**
                 * 判断数据库表中是否存在用户记录，若存在，则更新，不存在，则保存记录
                 */
                int count = userMapper.selectCount(user.getLoginacct());
                if (0 == count){
                    result = userMapper.insert(user);
                }else{
                    result = userMapper.updateByPrimaryKey(user);
                }
            }

        }
        return result;


    }
}
