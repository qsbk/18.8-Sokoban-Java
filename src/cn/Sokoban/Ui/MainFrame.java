package cn.Sokoban.Ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame implements MouseListener{
	
	private String URLDatas;//存储读取到的网页数据
	private JLabel[] LevelIco;//存储关卡图标
	private int[][] Data = new int[11][15];
	
	
	public MainFrame() throws Exception {
		this.ReadeURL();
		LevelIco = new JLabel[this.GetLevelCount()];//将数组初始化为和关卡数量相同
		this.LabIcoInit();
		BackGroundInit();
		FramerInit();
		this.addMouseListener(this);
	}
	
	//Frame初始化
	private void FramerInit() {
		this.setLayout(null);
		this.setTitle("推箱子 v1.0");
		this.setResizable(false);//禁止改变窗体大小
		this.setLocation(350, 100);
		this.setSize(800, 600);
		this.setVisible(true);
		
	}
	//读取关卡并载入
	private void ReadLevel(int num) {
		String LevelNum = "#" + num + "#";
		if(URLDatas.indexOf(LevelNum) == -1) {
			JOptionPane.showMessageDialog(null, "关卡正在飞速开发中.....", "提示",JOptionPane.WARNING_MESSAGE);
		}else {
			;
			int[][] LevelData = new int[11][15];
			String CutData = URLDatas.substring(URLDatas.indexOf(LevelNum)+3, URLDatas.indexOf(LevelNum,URLDatas.indexOf(LevelNum)+1));
			String[] Level = CutData.split(",");
			for(int i = 0; i < LevelData.length; i++) {
				int a = 0;
				for(int y = 0; y < LevelData[i].length; y++) {
					LevelData[i][y] = Integer.parseInt(Level[a]);
				}
			}
			this.dispose();
			new Level(LevelData);
			
		}
	}
	//初始化关卡图标
	private void LabIcoInit() {
		for(int i = 0; i < LevelIco.length; i++) {
			int x = 0;
			int y = 0;
			LevelIco[i] = new JLabel(String.valueOf(i+1),new ImageIcon("./image/box-no.png") ,JLabel.CENTER);
			LevelIco[i].setFont(new Font("Dialog", 1, 23));
			LevelIco[i].setForeground(new Color(255, 255, 255));
			LevelIco[i].setHorizontalTextPosition(JLabel.CENTER);
			LevelIco[i].addMouseListener(this);
			if( i >= 8 ) {
				x = i%8;
				y = i/8;
			}else {
				x = i;
			}
			LevelIco[i].setBounds(90*x+55, 90*y+40, 50, 50);
			this.add(LevelIco[i]);
		}
		
	}

	//读取URL中的关卡数量
	private int GetLevelCount() {
		String LevelCount;
		LevelCount = URLDatas.substring(URLDatas.indexOf("@@")+2, URLDatas.indexOf("@@",URLDatas.indexOf("@@")+1));
		return Integer.parseInt(LevelCount);
		
	}
	
	//用来读取URL的信息
	public void ReadeURL() throws Exception {
		String line;
		URL url = new URL("https://share.weiyun.com/0bc395832bbb476b2edf00e540e00463");
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));

		while ((line = reader.readLine()) != null) {
			URLDatas = URLDatas + line.replace(" ", "");
		}
		reader.close();

	}
	//背景初始化
	private void BackGroundInit() {
		JLabel lab_bg = new JLabel(new ImageIcon("./Image/bg.png"));
		//设置要添加的组建的位置和大小
		lab_bg.setBounds(1, 1, 800, 600);
		//将这个JLabel添加到窗体中
		this.add(lab_bg);
	}
	//打开关卡
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() instanceof JLabel) {
			JLabel lab = (JLabel) e.getSource();
			if(Integer.parseInt(lab.getText()) == 1) {
				this.dispose();
				new Level();	
			}else {
				this.ReadLevel(Integer.parseInt(lab.getText()));
			}
			
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
