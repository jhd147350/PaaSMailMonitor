package vianet.jhd;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class AlertItem {

	Composite c;
	ScrolledComposite scrolledComposite;

	//Display d;
	Composite alertC;
	int count;
	int currentCount;
	String id;
	Label l;
	Button btn;
	Thread t = new Thread(new Runnable() {

		@Override
		public void run() {
			for (int i = currentCount; i > 0; i--) {
				updateUI();
				currentCount--;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	});

	public AlertItem(Composite parent, Alert alert, Composite alertC, ScrolledComposite scrolledComposite) {
		this.id = alert.getId();
		this.alertC = alertC;
	//	this.d = display;
		this.scrolledComposite = scrolledComposite;
		this.count = this.currentCount = alert.getCountdown();
		c = new Composite(parent, SWT.NONE);
		l = new Label(c, SWT.NONE);
		btn = new Button(c, SWT.NONE);
		l.setText(id);
		btn.setText("×");
		btn.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				c.dispose();
				alertC.pack();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});

	}

	public void startCountdown() {
		t.start();
	}

	private void updateUI() {
		l.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				l.setText(id + " -> " + currentCount + "s");
				System.err.println("update" + id + " -> " + currentCount + "s");
				alertC.pack();// 重新计算整个alert部分的大小
				Point computeSize = alertC.computeSize(SWT.DEFAULT, SWT.DEFAULT);
				// 不设置minsize 就不出现滚动条
				scrolledComposite.setMinSize(computeSize);// 重新计算滚动条
			}
		});
	}

}
