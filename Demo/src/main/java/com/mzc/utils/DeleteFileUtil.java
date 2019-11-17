package com.mzc.utils;


import java.io.File;

public final  class DeleteFileUtil {
    private DeleteFileUtil(){};

    /**
     * 删除文件
     * @param filePath 文件路径
     * @return 删除成功true 失败false
     */
    public static boolean deleteFile(String filePath){
        File file =new File(filePath);
        if(file.exists()&&file.isFile()){
            if(file.delete()){
                System.out.println("删除单个文件成功:"+file.getAbsolutePath());
                return true;
            }else{
                System.out.println("删除单个文件失败:"+file.getAbsolutePath());
                return false;
            }
        }else{
            System.out.println("删除单个文件失败-不存在:"+file.getAbsolutePath());
            return false;
        }

    }

    /**
     * 删除目录
     * @param dirPath
     * @return 删除成功true 失败false
     */
    public static boolean deleteDirectory(String dirPath){
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dirPath.endsWith(File.separator))
            dirPath = dirPath + File.separator;
        File dirFile = new File(dirPath);
        //目录存在 且是目录
        if((!dirFile.exists())||(!dirFile.isDirectory())){
            System.out.println("删除目录失败："+dirFile.getAbsolutePath());
            return false;
        }
        boolean flag=true;
        //删除文件夹中的所有文件包括目录
        File[] files = dirFile.listFiles();
        for(int i=0;i<files.length;i++){
            // 删除子文件
            if(files[i].isFile()){
                flag=DeleteFileUtil.deleteDirectory(files[i].getAbsolutePath());
                if(!flag){
                    break;
                }
            }
            // 删除子目录
            if(files[i].isDirectory()){
                flag=DeleteFileUtil.deleteDirectory(files[i].getAbsolutePath());
                if(!flag){
                    break;
                }
            }
        }
        if(!flag){
            System.out.println("删除目录失败!:"+dirFile.getAbsolutePath());
            return false;
        }
        //删除当前目录
        if(dirFile.delete()){
            System.out.println("删除目录成功!:"+dirFile.getAbsolutePath());
            return true;
        }else{
            return false;
        }
    }
}
