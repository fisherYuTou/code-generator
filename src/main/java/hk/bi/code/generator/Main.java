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



            //自动生成mapper

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

