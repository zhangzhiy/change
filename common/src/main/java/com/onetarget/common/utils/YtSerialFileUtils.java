package com.onetarget.common.utils;

import android.app.Activity;
import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * 序列化文件的工具类
 */
public  class YtSerialFileUtils<E> {
	/***
	 * 存储单个对象
	 */
	public void saveObject(Context context, String fileName, E obj){
		if(context == null){
			return;
		}
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			oos.flush();
			return;
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		finally {
			try {
				if (oos != null)
					oos.close();
				if (fos != null)
					fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/***
	 * 从文件中读取单个对象
	 */
	@SuppressWarnings("unchecked")
	public E readObject(Context context, String fileName){
		if(context == null){
			return null;
		}
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = context.openFileInput(fileName);
			ois = new ObjectInputStream(fis);
			return (E) ois.readObject();
		}catch (Exception e) {
			e.printStackTrace();
			// 反序列化失败 - 删除缓存文件
			if (e instanceof InvalidClassException) {
				File data = new File(context.getFilesDir() + File.separator + fileName);
				if (data.exists())
					data.delete();
			}
		} finally {
			try {
				if (ois != null)
					ois.close();
				if (fis != null)
					fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/***
	 * 删除序列化的文件
	 */
	public boolean deleteObject(Activity context, String fileName){
		if(context == null){
			return false;
		}
		try {
			return context.deleteFile(fileName);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/***
	 * 存储对象集合
	 */
	public void saveObjects(Context context, String fileName, ArrayList<E> obj){
		if(context == null){
			return;
		}
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			oos.flush();
		}catch (Exception e) {
			e.printStackTrace();
			return;
		} finally {
			try {
				if (oos != null)
					oos.close();
				if (fos != null)
					fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/***
	 * 从文件中读取对象集合
	 */
	public ArrayList<E> readObjects(Context context, String fileName){
		if(context == null){
			return null;
		}
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = context.openFileInput(fileName);
			ois = new ObjectInputStream(fis);
			return (ArrayList<E>) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null)
					ois.close();
				if (fis != null)
					fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/***
	 * 存储对象集合
	 * 退出后，不清除数据
	 */
	public void saveMessageObjects(Context context, String fileName, ArrayList<E> obj){
		if(context == null){
			return;
		}
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			File myFile=new File(context.getCacheDir()+fileName);
			fos = new FileOutputStream(myFile);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			oos.flush();
		}catch (Exception e) {
			e.printStackTrace();
			return;
		} finally {
			try {
				if (oos != null)
					oos.close();
				if (fos != null)
					fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/***
	 * 从文件中读取对象集合
	 * 退出后，不清除数据
	 */
	public ArrayList<E> readMessageObjects(Context context, String fileName){
		if(context == null){
			return null;
		}
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			File myFile=new File(context.getCacheDir()+fileName);
			fis = new FileInputStream(myFile);
			ois = new ObjectInputStream(fis);
			return (ArrayList<E>) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null)
					ois.close();
				if (fis != null)
					fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
