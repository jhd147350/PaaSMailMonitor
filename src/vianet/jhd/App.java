package vianet.jhd;

import javax.swing.SwingUtilities;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import swing2swt.layout.BorderLayout;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;

public class App extends ApplicationWindow {

	private Composite needHandleC;
	private Composite ignoreC;
	private Composite unknownC;
	private Composite container;
	private Composite alertC;
	private ScrolledComposite scrolledComposite;// 滚动条

	/**
	 * Create the application window.
	 */
	public App() {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();

	}

	/**
	 * Create contents of the application window.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		container.setSize(500, 600);
		container.setLayout(new BorderLayout(0, 0));
		container.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseScrolled(MouseEvent arg0) {
				System.out.println(arg0.count);

			}
		});
		{
			Button btnStop = new Button(container, SWT.NONE);
			btnStop.setLayoutData(BorderLayout.SOUTH);
			btnStop.setText("stop");
			btnStop.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					statrListen();
					// TODO 只有获得焦点，滚动条才能响应鼠标滑轮去滚动
					scrolledComposite.setFocus();
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {

				}
			});
		}

		scrolledComposite = new ScrolledComposite(container, SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		scrolledComposite.setAlwaysShowScrollBars(true);
		scrolledComposite.setLayout(new FillLayout());
		// scrolledComposite.setBounds(20, 25, 230, 220);
		// scrolledComposite.setSize(500, 600);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setMinSize(300, 400);//
		// scrolledComposite.addControlListener(listener);
		scrolledComposite.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {

				Point computeSize = scrolledComposite.getContent().computeSize(SWT.DEFAULT, SWT.DEFAULT);
				// 不设置minsize 就不出现滚动条
				scrolledComposite.setMinSize(computeSize);

			}
		});
		scrolledComposite.setFocus();

		/*
		 * scrolledComposite.addMouseWheelListener(new MouseWheelListener() {
		 * 
		 * @Override public void mouseScrolled(MouseEvent me) {
		 * System.out.println("滑轮滚动时间"); System.out.println(me.count); } });
		 */
		// TODO 根本不需要自己去控制滚动条的位置，只要让滚动控件自己获得焦点即可。
		/*
		 * container.addMouseWheelListener(new MouseWheelListener() {
		 * 
		 * @Override public void mouseScrolled(MouseEvent arg0) {
		 * System.out.println(arg0.count); //scrolledComposite.moveAbove();
		 * //scrolledComposite.setFocus();
		 * 
		 * } });
		 */

		alertC = new Composite(scrolledComposite, SWT.NONE);
		scrolledComposite.setContent(alertC);
		alertC.pack();

		alertC.setLayoutData(BorderLayout.CENTER);
		RowLayout rl_alertC = new RowLayout(SWT.VERTICAL);
		rl_alertC.wrap = false;
		alertC.setLayout(rl_alertC);

		// scrolledComposite.setMinHeight(600);

		needHandleC = new Composite(alertC, SWT.NONE);
		needHandleC.setLayout(new RowLayout(SWT.VERTICAL));
		{
			Label lblNewLabel = new Label(needHandleC, SWT.NONE);
			lblNewLabel.setText("需要处理");
		}
		{

			Label lblNewLabel_1 = new Label(needHandleC, SWT.NONE);
			lblNewLabel_1.setText("CN:bmxcn.CYP.estado.PHP : st_runtime_down -> 30s");
			lblNewLabel_1.setText("CN:bmxcn.CYP.estado.PHP : st_runtime_down -> 31s");

		}

		ignoreC = new Composite(alertC, SWT.NONE);
		ignoreC.setLayout(new RowLayout(SWT.VERTICAL));
		{
			Label lblNewLabel = new Label(ignoreC, SWT.NONE);
			lblNewLabel.setText("已忽略");
		}

		unknownC = new Composite(alertC, SWT.NONE);
		unknownC.setLayout(new RowLayout(SWT.VERTICAL));
		{
			Label lblNewLabel = new Label(unknownC, SWT.NONE);
			lblNewLabel.setText("未识别");
		}

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Create the menu manager.
	 * 
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * 
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * 
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			App window = new App();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
			System.out.println("结束");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * 
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("configureShell-jhd");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

	public void statrListen() {
		System.out.println("开始监听");
		add();
	}

	public void add() {
		Alert a = new Alert();
		a.setId("ididididididididi");
		a.setCountdown(70);
		AlertItem alertItem = new AlertItem(needHandleC, a, alertC, scrolledComposite);
		alertItem.startCountdown();

		Runnable r = new Runnable() {
			@Override
			public void run() {
				/*
				 * Composite c = new Composite(needHandleC, SWT.NONE); RowLayout rl = new
				 * RowLayout(SWT.HORIZONTAL); c.setLayout(rl);
				 * 
				 * Label lblNewLabel_1 = new Label(c, SWT.NONE);
				 * lblNewLabel_1.setText("CN:bmxcn.CYP.estado.PHP : st_runtime_down -> ");
				 * 
				 * Button btnStop = new Button(c, SWT.NONE); btnStop.setText("X");
				 * 
				 * btnStop.addSelectionListener(new SelectionListener() {
				 * 
				 * @Override public void widgetSelected(SelectionEvent arg0) { c.dispose();
				 * alertC.pack(); }
				 * 
				 * @Override public void widgetDefaultSelected(SelectionEvent arg0) { } });
				 * 
				 * for (int i = 0; i <= 60; i++) { try { Thread.sleep(1000); } catch
				 * (InterruptedException e) { e.printStackTrace(); }
				 * lblNewLabel_1.setText("CN:bmxcn.CYP.estado.PHP : st_runtime_down -> " + i +
				 * "s"); alertC.pack();// 重新计算整个alert部分的大小 }
				 * 
				 * System.out.println("计时完成"); //
				 * lblNewLabel_1.setText("CN:bmxcn.CYP.estado.PHP : st_runtime_down -> 2120s");
				 * // container.pack(); // getShell().pack();
				 * 
				 * // Point computeSize =
				 * scrolledComposite.getContent().computeSize(SWT.DEFAULT, // SWT.DEFAULT);
				 * Point computeSize = alertC.computeSize(SWT.DEFAULT, SWT.DEFAULT); //
				 * 不设置minsize 就不出现滚动条 scrolledComposite.setMinSize(computeSize);// 重新计算滚动条
				 */
			}
		};
		// TODO 非ui线程不能更新ui
		// Display.getDefault().syncExec(r);
		// Display.getCurrent().asyncExec(r);
	}
}
