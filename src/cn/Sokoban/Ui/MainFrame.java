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
	
	private String URLDatas;//�洢��ȡ������ҳ����
	private JLabel[] LevelIco;//�洢�ؿ�ͼ��
	private int[][] Data = new int[11][15];
	
	
	public MainFrame() throws Exception {
		this.ReadeURL();
		LevelIco = new JLabel[this.GetLevelCount()];//�������ʼ��Ϊ�͹ؿ�������ͬ
		this.LabIcoInit();
		BackGroundInit();
		FramerInit();
		this.addMouseListener(this);
	}
	
	//Frame��ʼ��
	private void FramerInit() {
		this.setLayout(null);
		this.setTitle("������ v1.0");
		this.setResizable(false);//��ֹ�ı䴰���С
		this.setLocation(350, 100);
		this.setSize(800, 600);
		this.setVisible(true);
		
	}
	//��ȡ�ؿ�������
	private void ReadLevel(int num) {
		String LevelNum = "#" + num + "#";
		if(URLDatas.indexOf(LevelNum) == -1) {
			JOptionPane.showMessageDialog(null, "�ؿ����ڷ��ٿ�����.....", "��ʾ",JOptionPane.WARNING_MESSAGE);
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
	//��ʼ���ؿ�ͼ��
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

	//��ȡURL�еĹؿ�����
	private int GetLevelCount() {
		String LevelCount;
		LevelCount = URLDatas.substring(URLDatas.indexOf("@@")+2, URLDatas.indexOf("@@",URLDatas.indexOf("@@")+1));
		return Integer.parseInt(LevelCount);
		
	}
	
	//������ȡURL����Ϣ
	public void ReadeURL() throws Exception {
		String line;
		URL url = new URL("https://share.weiyun.com/0bc395832bbb476b2edf00e540e00463");
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));

		while ((line = reader.readLine()) != null) {
			URLDatas = URLDatas + line.replace(" ", "");
		}
		reader.close();

	}
	//������ʼ��
	private void BackGroundInit() {
		JLabel lab_bg = new JLabel(new ImageIcon("./Image/bg.png"));
		//����Ҫ��ӵ��齨��λ�úʹ�С
		lab_bg.setBounds(1, 1, 800, 600);
		//�����JLabel��ӵ�������
		this.add(lab_bg);
	}
	//�򿪹ؿ�
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
