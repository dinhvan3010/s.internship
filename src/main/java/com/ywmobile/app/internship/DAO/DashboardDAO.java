package com.ywmobile.app.internship.DAO;

import com.ywmobile.app.internship.DTO.Dashboard;
import com.ywmobile.app.internship.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository
public class DashboardDAO {

    public Dashboard dashboardStaff(int id) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        Dashboard dashboard = session.selectOne("StatisticMapper.statisticForStaff", id);
        session.commit();
        session.close();
        return dashboard;
    }


    public Dashboard dashboardAdmin() {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        Dashboard dashboard = session.selectOne("StatisticMapper.statisticForAdmin");
        session.commit();
        session.close();
        return dashboard;
    }

}

