package com.ywmobile.app.internship.DAO;

import com.ywmobile.app.internship.DTO.LeaveDTO;
import com.ywmobile.app.internship.DTO.Paging;
import com.ywmobile.app.internship.MyBatisUtil;
import com.ywmobile.app.internship.model.LeaveRequest;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeaveRequestDAO {


    public int getCountLeave(String keyword) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        int totals = session.selectOne("LeaveMapper.getCountLeave", keyword);
        session.commit();
        session.close();
        return totals;
    }


    public List<LeaveDTO> getListLeaveRequestAdmin(Paging paging) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        List<LeaveDTO> leaves = session.selectList("LeaveMapper.getListLeaveRequest", paging);
        session.commit();
        session.close();
        return leaves;
    }

    public int getCountListLeaveRequest(String keyword) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        int count = session.selectOne("LeaveMapper.getCountListLeaveRequest", keyword);
        session.commit();
        session.close();
        return count;
    }


    public List<LeaveRequest> getLeaveForStaff(Paging paging) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        List<LeaveRequest> leaveRequests = session.selectList("LeaveMapper.getListLeaveForStaff", paging);
        session.commit();
        session.close();
        return leaveRequests;
    }

    public void save(LeaveRequest leaveRequest) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.insert("LeaveMapper.insertLeave", leaveRequest);
        session.commit();
        session.close();
    }

    public void update(LeaveRequest leaveRequest) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.update("LeaveMapper.updateStatusLeave", leaveRequest);
        session.commit();
        session.close();
    }

    public LeaveRequest findById(int id) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        LeaveRequest leaveRequest = session.selectOne("LeaveMapper.getById", id);
        session.commit();
        session.close();
        return leaveRequest;
    }

    public List<LeaveDTO> getLeaveRequestPaging(Paging paging) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        List<LeaveDTO> leaveDTOS = session.selectList("LeaveMapper.getLeaveRequestPaging", paging);
        session.commit();
        session.close();
        return leaveDTOS;
    }

    public int getTotalItems(Paging paging) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        int count = session.selectOne("LeaveMapper.getCountListLeaveRequestForStaff", paging);
        session.commit();
        session.close();
        return count;
    }
}

