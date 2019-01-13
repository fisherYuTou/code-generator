package hk.bi.code.generator;

import hk.bi.code.generator.bean.TableBean;
import hk.bi.code.generator.utils.TableUtil;
import hk.bi.code.generator.utils.FreeMakerUtil;
import hk.bi.code.generator.utils.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.sql.*;
import java.util.*;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    //可配置1111132323233
    private static String beanBasePath = null;
    private static String mappingBasePath = null;
    private static String mapperBasePath = null;
    private static String serviceBasePath = null;
    private static String serviceImplBasePath = null;
    private static String controllerBasePath = null;
    private static String packageName = null;
    private static String schemaName = null;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        // 读取orm文件生成路径
        Properties props = PropertiesUtil.getProperties("common.properties");
        beanBasePath = props.getProperty("beanBasePath");
        mappingBasePath = props.getProperty("mappingBasePath");
        mapperBasePath = props.getProperty("mapperBasePath");
        serviceBasePath = props.getProperty("serviceBasePath");
        serviceImplBasePath = props.getProperty("serviceImplBasePath");
        controllerBasePath = props.getProperty("controllerBasePath");
       packageName= props.getProperty("packageName");
        schemaName = props.getProperty("schemaName");
        if("".equals(schemaName)){
            schemaName = "%";
        }

        args = new String[]{"n"};

        // 如果表名不为空
        if(args.length > 0){
            String tableList = args[0];
            Long startTime = System.currentTimeMillis();
            List<String> tables = Arrays.asList(tableList.split(","));
            for(String tableName : tables){
                buildModel(schemaName,tableName);
            }
            Long endTime = System.currentTimeMillis();
            System.out.println("代码生成成功，耗时 ："+(endTime-startTime)+" 毫秒");
        }else{
            logger.info("=====================jar后面请带表名参数,多表请用逗号隔开!=============================");
        }

    }

    /** 按表名生成orm框架
     *  @param tableName 表名称
     */
    public static void buildModel(String schemaName,String tableName){
        TableBean tableBean = null;

        try {
            tableBean = TableUtil.getTableInfo(schemaName,tableName);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }


        if(StringUtils.isNotBlank(tableBean.getTableName())){
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("tableInfo", tableBean);
            data.put("packageName", packageName);

            FreeMakerUtil freeMakerUtil = new FreeMakerUtil();

            // 如果主键个数大于1， 则自动生成主键bean
            if(tableBean.getKeyCount() > 1){
                String beanOutputFile = beanBasePath+packageName.replace(".", File.separator)+File.separator+"domain"+File.separator+tableBean.getClassName()+"Key.java";
                freeMakerUtil.generateFile("keyModel.ftl",  data, beanOutputFile);
            }

            //自动生成bean
            String beanOutputFile = beanBasePath+packageName.replace(".", File.separator)+File.separator+"domain"+File.separator+tableBean.getClassName()+".java";
            freeMakerUtil.generateFile("beanModel.ftl",  data, beanOutputFile);

            //自动生成mappings
            String mappingOutputFile = mappingBasePath+File.separator+"mappings"+File.separator+tableBean.getClassName()+".xml";
            freeMakerUtil.generateFile("mappingModel.ftl",  data, mappingOutputFile);

            // 自动生成mapper
            String mapperOutputFile = mapperBasePath+packageName.replace(".", File.separator)+File.separator+"mapper"+File.separator+tableBean.getClassName()+"Mapper.java";
            freeMakerUtil.generateFile("mapperModel.ftl",  data, mapperOutputFile);

            //自动生成service
            String serviceOutputFile = serviceBasePath+packageName.replace(".", File.separator)+File.separator+"service"+File.separator+tableBean.getClassName()+"Service.java";
            freeMakerUtil.generateFile("serviceModel.ftl",  data, serviceOutputFile);

            //自动生成serviceImpl
            String serviceImplOutputFile = serviceImplBasePath+packageName.replace(".", File.separator)+File.separator+"service"+File.separator+"impl"+File.separator+tableBean.getClassName()+"ServiceImpl.java";
            freeMakerUtil.generateFile("serviceImplModel.ftl",  data, serviceImplOutputFile);

            //自动生成controller
            String controllerOutputFile = controllerBasePath+packageName.replace(".", File.separator)+File.separator+"controller"+File.separator+tableBean.getClassName()+"Controller.java";
            freeMakerUtil.generateFile("controllerModel.ftl",  data, controllerOutputFile);

            logger.info("表 " +tableName+" orm 生成完毕!");
        }else{
            logger.info("表 " +tableName+" 不存在");
        }

    }


}
