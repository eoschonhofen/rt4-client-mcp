import java.applet.Applet;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Hashtable;
import java.util.Vector;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("signlink!ll")
public final class SignLink implements Runnable {

	@OriginalMember(owner = "signlink!ll", name = "o", descriptor = "Ljava/lang/String;")
	public static String javaVersion;

	@OriginalMember(owner = "signlink!ll", name = "n", descriptor = "Ljava/lang/String;")
	public static String osNameLower;

	@OriginalMember(owner = "signlink!ll", name = "l", descriptor = "Ljava/lang/String;")
	private static String osName;

	@OriginalMember(owner = "signlink!ll", name = "v", descriptor = "Ljava/lang/String;")
	private static String userHome;

	@OriginalMember(owner = "signlink!ll", name = "j", descriptor = "Ljava/lang/String;")
	private static String osVersion;

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "Ljava/lang/String;")
	public static String javaVendor;

	@OriginalMember(owner = "signlink!ll", name = "b", descriptor = "Ljava/lang/String;")
	private static String osArch;

	@OriginalMember(owner = "signlink!ll", name = "u", descriptor = "Ljava/lang/reflect/Method;")
	public static Method setFocusCycleRoot;

	@OriginalMember(owner = "signlink!ll", name = "r", descriptor = "Ljava/lang/reflect/Method;")
	public static Method setTraversalKeysEnabled;

	@OriginalMember(owner = "signlink!ll", name = "e", descriptor = "I")
	public static final int anInt5928 = 1;

	@OriginalMember(owner = "signlink!ll", name = "w", descriptor = "Ljava/util/Hashtable;")
	private static final Hashtable aHashtable2 = new Hashtable(16);

	@OriginalMember(owner = "signlink!ll", name = "q", descriptor = "J")
	private static volatile long aLong1314 = 0L;

	@OriginalMember(owner = "signlink!ll", name = "A", descriptor = "Lsignlink!ai;")
	private AudioSource anInterface10_2;

	@OriginalMember(owner = "signlink!ll", name = "g", descriptor = "Lsignlink!qm;")
	public FileOnDisk cacheDat = null;

	@OriginalMember(owner = "signlink!ll", name = "p", descriptor = "Lsignlink!im;")
	private PrivilegedRequest task = null;

	@OriginalMember(owner = "signlink!ll", name = "f", descriptor = "Z")
	private boolean isClosed = false;

	@OriginalMember(owner = "signlink!ll", name = "h", descriptor = "Lsignlink!qm;")
	public FileOnDisk masterIndex = null;

	@OriginalMember(owner = "signlink!ll", name = "d", descriptor = "Lsignlink!qm;")
	public FileOnDisk uidDat = null;

	@OriginalMember(owner = "signlink!ll", name = "y", descriptor = "Lsignlink!im;")
	private PrivilegedRequest current = null;

	@OriginalMember(owner = "signlink!ll", name = "i", descriptor = "Ljava/applet/Applet;")
	public Applet applet = null;

	@OriginalMember(owner = "signlink!ll", name = "x", descriptor = "Ljava/lang/String;")
	private final String game;

	@OriginalMember(owner = "signlink!ll", name = "z", descriptor = "I")
	private final int filestore;

	@OriginalMember(owner = "signlink!ll", name = "k", descriptor = "Ljava/awt/EventQueue;")
	public EventQueue eventQueue;

	@OriginalMember(owner = "signlink!ll", name = "c", descriptor = "[Lsignlink!qm;")
	public FileOnDisk[] cacheIndex;

	@OriginalMember(owner = "signlink!ll", name = "t", descriptor = "Lsignlink!e;")
	private FullScreen aClass210_1;

	@OriginalMember(owner = "signlink!ll", name = "s", descriptor = "Lsignlink!g;")
	private CustomCursor customCursor;

	@OriginalMember(owner = "signlink!ll", name = "m", descriptor = "Ljava/lang/Thread;")
	private final Thread thread;

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(ZLjava/lang/String;)Lsignlink!qm;")
	private static FileOnDisk method5117(@OriginalArg(1) String arg0) {
		@Pc(41) String[] local41 = new String[] { "c:/rscache/", "/rscache/", userHome, "c:/windows/", "c:/winnt/", "c:/", "/tmp/", "" };
		for (@Pc(43) int local43 = 0; local43 < local41.length; local43++) {
			@Pc(51) String local51 = local41[local43];
			if (local51.length() <= 0 || (new File(local51)).exists()) {
				try {
					return new FileOnDisk(new File(local51, "jagex_" + arg0 + "_preferences.dat"), "rw", 10000L);
				} catch (@Pc(84) Exception ignore) {
				}
			}
		}
		return null;
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(Ljava/lang/String;IZLjava/lang/String;)Ljava/io/File;")
	public static File method5127(@OriginalArg(0) String arg0, @OriginalArg(1) int arg1, @OriginalArg(3) String arg2) {
		@Pc(4) File local4 = (File) aHashtable2.get(arg2);
		if (local4 != null) {
			return local4;
		}
		@Pc(53) String[] local53 = new String[] { "c:/rscache/", "/rscache/", "c:/windows/", "c:/winnt/", "c:/", userHome, "/tmp/", "" };
		@Pc(78) String[] local78 = new String[] { ".jagex_cache_" + arg1, ".file_store_" + arg1 };
		for (@Pc(80) int local80 = 0; local80 < 2; local80++) {
			for (@Pc(87) int local87 = 0; local87 < local78.length; local87++) {
				for (@Pc(93) int local93 = 0; local93 < local53.length; local93++) {
					@Pc(128) String local128 = local53[local93] + local78[local87] + "/" + (arg0 == null ? "" : arg0 + "/") + arg2;
					@Pc(130) RandomAccessFile local130 = null;
					try {
						@Pc(135) File local135 = new File(local128);
						if (local80 != 0 || local135.exists()) {
							@Pc(145) String local145 = local53[local93];
							if (local80 != 1 || local145.length() <= 0 || (new File(local145)).exists()) {
								(new File(local53[local93] + local78[local87])).mkdir();
								if (arg0 != null) {
									(new File(local53[local93] + local78[local87] + "/" + arg0)).mkdir();
								}
								local130 = new RandomAccessFile(local135, "rw");
								@Pc(210) int local210 = local130.read();
								local130.seek(0L);
								local130.write(local210);
								local130.seek(0L);
								local130.close();
								aHashtable2.put(arg2, local135);
								return local135;
							}
						}
					} catch (@Pc(229) Exception local229) {
						try {
							if (local130 != null) {
								local130.close();
							}
						} catch (@Pc(239) Exception local239) {
						}
					}
				}
			}
		}
		throw new RuntimeException();
	}

	@OriginalMember(owner = "signlink!ll", name = "<init>", descriptor = "(Ljava/applet/Applet;ILjava/lang/String;I)V")
	public SignLink(@OriginalArg(0) Applet applet, @OriginalArg(1) int filestore, @OriginalArg(2) String game, @OriginalArg(3) int archiveCount) throws Exception {
		javaVersion = "1.1";
		this.game = game;
		this.filestore = filestore;
		this.applet = applet;
		javaVendor = "Unknown";

		try {
			javaVendor = System.getProperty("java.vendor");
			javaVersion = System.getProperty("java.version");
		} catch (@Pc(43) Exception ignore) {
		}

		try {
			osName = System.getProperty("os.name");
		} catch (@Pc(48) Exception ex) {
			osName = "Unknown";
		}

		osNameLower = osName.toLowerCase();

		try {
			osArch = System.getProperty("os.arch").toLowerCase();
		} catch (@Pc(59) Exception ex) {
			osArch = "";
		}

		try {
			osVersion = System.getProperty("os.version").toLowerCase();
		} catch (@Pc(67) Exception ex) {
			osVersion = "";
		}

		try {
			userHome = System.getProperty("user.home");
			if (userHome != null) {
				userHome = userHome + "/";
			}
		} catch (@Pc(86) Exception ignore) {
		}

		if (userHome == null) {
			userHome = "~/";
		}

		try {
			this.eventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();
		} catch (@Pc(97) Throwable ignore) {
		}

		try {
			if (applet == null) {
				setTraversalKeysEnabled = Class.forName("java.awt.Component").getDeclaredMethod("setFocusTraversalKeysEnabled", Boolean.TYPE);
			} else {
				setTraversalKeysEnabled = applet.getClass().getMethod("setFocusTraversalKeysEnabled", Boolean.TYPE);
			}
		} catch (@Pc(125) Exception ignore) {
		}

		try {
			if (applet == null) {
				setFocusCycleRoot = Class.forName("java.awt.Container").getDeclaredMethod("setFocusCycleRoot", Boolean.TYPE);
			} else {
				setFocusCycleRoot = applet.getClass().getMethod("setFocusCycleRoot", Boolean.TYPE);
			}
		} catch (@Pc(153) Exception ignore) {
		}

		this.uidDat = new FileOnDisk(method5127(null, this.filestore, "random.dat"), "rw", 25L);

		this.cacheDat = new FileOnDisk(method5127(this.game, this.filestore, "main_file_cache.dat2"), "rw", 104857600L);
		this.masterIndex = new FileOnDisk(method5127(this.game, this.filestore, "main_file_cache.idx255"), "rw", 1048576L);

		this.cacheIndex = new FileOnDisk[archiveCount];
		for (@Pc(200) int local200 = 0; local200 < archiveCount; local200++) {
			this.cacheIndex[local200] = new FileOnDisk(method5127(this.game, this.filestore, "main_file_cache.idx" + local200), "rw", 1048576L);
		}

		try {
			this.aClass210_1 = new FullScreen();
		} catch (@Pc(239) Throwable ignore) {
		}

		try {
			this.customCursor = new CustomCursor();
		} catch (@Pc(246) Throwable ignore) {
		}

		@Pc(249) ThreadGroup local249 = Thread.currentThread().getThreadGroup();
		for (@Pc(252) ThreadGroup local252 = local249.getParent(); local252 != null; local252 = local252.getParent()) {
			local249 = local252;
		}
		@Pc(263) Thread[] local263 = new Thread[1000];
		local249.enumerate(local263);
		for (@Pc(269) int local269 = 0; local269 < local263.length; local269++) {
			if (local263[local269] != null && local263[local269].getName().startsWith("AWT")) {
				local263[local269].setPriority(1);
			}
		}

		this.isClosed = false;
		this.thread = new Thread(this);
		this.thread.setPriority(10);
		this.thread.setDaemon(true);
		this.thread.start();
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(I)V")
	public final void method5110() {
		aLong1314 = MonotonicTime.currentTime() + 5000L;
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(Z)Z")
	public final boolean method5111() {
		return this.aClass210_1 != null;
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(Ljava/lang/String;I)Lsignlink!im;")
	public final PrivilegedRequest method5112(@OriginalArg(0) String arg0) {
		return this.newRequest(12, 0, arg0, 0);
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "([IIILjava/awt/Component;Ljava/awt/Point;I)Lsignlink!im;")
	public final PrivilegedRequest method5113(@OriginalArg(0) int[] arg0, @OriginalArg(2) int arg1, @OriginalArg(3) Component arg2, @OriginalArg(4) Point arg3, @OriginalArg(5) int arg4) {
		return this.newRequest(17, arg4, new Object[] { arg2, arg0, arg3 }, arg1);
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(IILjava/lang/Object;II)Lsignlink!im;")
	private PrivilegedRequest newRequest(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) Object arg2, @OriginalArg(3) int arg3) {
		@Pc(3) PrivilegedRequest local3 = new PrivilegedRequest();
		local3.intArg2 = arg1;
		local3.intArg = arg3;
		local3.type = arg0;
		local3.objArg = arg2;

		synchronized (this) {
			if (this.task == null) {
				this.task = this.current = local3;
			} else {
				this.task.next = local3;
				this.task = local3;
			}

			this.notify();
			return local3;
		}
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(Ljava/awt/Frame;I)Lsignlink!im;")
	public final PrivilegedRequest method5115(@OriginalArg(0) Frame arg0) {
		return this.newRequest(7, 0, arg0, 0);
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(ZLjava/net/URL;)Lsignlink!im;")
	public final PrivilegedRequest urlreq(@OriginalArg(1) URL arg0) {
		return this.newRequest(4, 0, arg0, 0);
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(BLjava/lang/String;I)Lsignlink!im;")
	public final PrivilegedRequest socketreq(@OriginalArg(1) String arg0, @OriginalArg(2) int arg1) {
		return this.newRequest(1, 0, arg0, arg1);
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(Ljava/lang/Class;I)Lsignlink!im;")
	public final PrivilegedRequest method5121(@OriginalArg(0) Class arg0) {
		return this.newRequest(11, 0, arg0, 0);
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(Ljava/lang/Class;[Ljava/lang/Class;ILjava/lang/String;)Lsignlink!im;")
	public final PrivilegedRequest method5122(@OriginalArg(0) Class arg0, @OriginalArg(1) Class[] arg1, @OriginalArg(3) String arg2) {
		return this.newRequest(8, 0, new Object[] { arg0, arg2, arg1 }, 0);
	}

	public static boolean copy(InputStream source, String destination) {
		boolean succeess = true;

		try {
			Files.copy(source, Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ex) {
			ex.printStackTrace();
			succeess = false;
		}

		return succeess;

	}

	@OriginalMember(owner = "signlink!ll", name = "run", descriptor = "()V")
	@Override
	public final void run() {
		while (true) {
			@Pc(16) PrivilegedRequest req;
			synchronized (this) {
				while (true) {
					if (this.isClosed) {
						return;
					}

					if (this.current != null) {
						req = this.current;
						this.current = this.current.next;
						if (this.current == null) {
							this.task = null;
						}
						break;
					}

					try {
						this.wait();
					} catch (@Pc(33) InterruptedException ignore) {
					}
				}
			}

			try {
				@Pc(45) int type = req.type;
				if (type == 1) {
					if (aLong1314 > MonotonicTime.currentTime()) {
						throw new IOException();
					}

					req.result = new Socket(InetAddress.getByName((String) req.objArg), req.intArg);
				} else if (type == 2) {
					@Pc(813) Thread thread = new Thread((Runnable) req.objArg);
					thread.setDaemon(true);
					thread.start();
					thread.setPriority(req.intArg);
					req.result = thread;
				} else if (type == 4) {
					if (aLong1314 > MonotonicTime.currentTime()) {
						throw new IOException();
					}

					req.result = new DataInputStream(((URL) req.objArg).openStream());
				} else {
					@Pc(687) Object[] local687;
					if (type == 8) {
						local687 = (Object[]) req.objArg;
						if (((Class) local687[0]).getClassLoader() == null) {
							throw new SecurityException();
						}

						req.result = ((Class) local687[0]).getDeclaredMethod((String) local687[1], (Class[]) local687[2]);
					} else if (type == 9) {
						local687 = (Object[]) req.objArg;
						if (((Class) local687[0]).getClassLoader() == null) {
							throw new SecurityException();
						}

						req.result = ((Class) local687[0]).getDeclaredField((String) local687[1]);
					} else {
						@Pc(147) String local147;
						if (type == 3) {
							if (MonotonicTime.currentTime() < aLong1314) {
								throw new IOException();
							}

							local147 = (req.intArg >> 24 & 0xFF) + "." + (req.intArg >> 16 & 0xFF) + "." + (req.intArg >> 8 & 0xFF) + "." + (req.intArg & 0xFF);
							req.result = InetAddress.getByName(local147).getHostName();
						} else if (type == 5) {
							req.result = this.aClass210_1.getDisplayModes();
						} else if (type == 6) {
							@Pc(168) Frame local168 = new Frame("Jagex Full Screen");
							req.result = local168;
							local168.setResizable(false);
							this.aClass210_1.enter(req.intArg2 & 0xFFFF, req.intArg2 >> 16, req.intArg & 0xFFFF, local168, req.intArg >>> 16);
						} else if (type == 7) {
							this.aClass210_1.exit();
						} else if (type == 10) {
							@Pc(217) Class[] local217 = new Class[] { Class.forName("java.lang.Class"), Class.forName("java.lang.String") };
							@Pc(219) Runtime local219 = Runtime.getRuntime();
							@Pc(230) Method local230;
							if (!osNameLower.startsWith("mac")) {
								local230 = Class.forName("java.lang.Runtime").getDeclaredMethod("loadLibrary0", local217);
								local230.setAccessible(true);
								local230.invoke(local219, req.objArg, "jawt");
								local230.setAccessible(false);
							}
							local230 = Class.forName("java.lang.Runtime").getDeclaredMethod("load0", local217);
							local230.setAccessible(true);
							int arch = Integer.parseInt(System.getProperty("sun.arch.data.model")); // returns 32 or 64
							if (osNameLower.startsWith("linux") || osNameLower.startsWith("sunos")) {
								copy(getClass().getResourceAsStream("libgluegen-rt" + arch + ".so"), method5127(this.game, this.filestore, "libgluegen-rt.so").toString());
								local230.invoke(local219, req.objArg, method5127(this.game, this.filestore, "libgluegen-rt.so").toString());
								@Pc(399) Class local399 = ((Class) req.objArg).getClassLoader().loadClass("com.sun.opengl.impl.x11.DRIHack");
								local399.getMethod("begin").invoke(null);
								copy(getClass().getResourceAsStream("libjogl_" + arch + ".so"), method5127(this.game, this.filestore, "libjogl.so").toString());
								local230.invoke(local219, req.objArg, method5127(this.game, this.filestore, "libjogl.so").toString());
								local399.getMethod("end").invoke(null);
								copy(getClass().getResourceAsStream("libjogl_awt_" + arch + ".so"), method5127(this.game, this.filestore, "libjogl_awt.so").toString());
								local230.invoke(local219, req.objArg, method5127(this.game, this.filestore, "libjogl_awt.so").toString());
							} else if (osNameLower.startsWith("mac")) {
								copy(getClass().getResourceAsStream("libjogl.jnilib"), method5127(this.game, this.filestore, "libjogl.jnilib").toString());
								local230.invoke(local219, req.objArg, method5127(this.game, this.filestore, "libjogl.jnilib").toString());
								copy(getClass().getResourceAsStream("libjogl_awt.jnilib"), method5127(this.game, this.filestore, "libjogl_awt.jnilib").toString());
								local230.invoke(local219, req.objArg, method5127(this.game, this.filestore, "libjogl_awt.jnilib").toString());
							} else if (osNameLower.startsWith("win")) {
								copy(getClass().getResourceAsStream("jogl_" + arch + ".dll"), method5127(this.game, this.filestore, "jogl.dll").toString());
								local230.invoke(local219, req.objArg, method5127(this.game, this.filestore, "jogl.dll").toString());
								copy(getClass().getResourceAsStream("jogl_awt_" + arch + ".dll"), method5127(this.game, this.filestore, "jogl_awt.dll").toString());
								local230.invoke(local219, req.objArg, method5127(this.game, this.filestore, "jogl_awt.dll").toString());
							} else {
								throw new Exception();
							}
							local230.setAccessible(false);
						} else {
							@Pc(490) int local490;
							if (type == 11) {
								@Pc(477) Field local477 = Class.forName("java.lang.ClassLoader").getDeclaredField("nativeLibraries");
								local477.setAccessible(true);
								@Pc(488) Vector local488 = (Vector) local477.get(((Class) req.objArg).getClassLoader());
								for (local490 = 0; local488.size() > local490; local490++) {
									@Pc(502) Object local502 = local488.elementAt(local490);
									@Pc(509) Method local509 = local502.getClass().getDeclaredMethod("finalize");
									local509.setAccessible(true);
									local509.invoke(local502);
									local509.setAccessible(false);
									@Pc(526) Field local526 = local502.getClass().getDeclaredField("handle");
									local526.setAccessible(true);
									local526.set(local502, Integer.valueOf(0));
									local526.setAccessible(false);
								}
								local477.setAccessible(false);
							} else if (type == 12) {
								local147 = (String) req.objArg;
								@Pc(558) FileOnDisk local558 = method5117(local147);
								req.result = local558;
							} else if (type == 14) {
								@Pc(570) int local570 = req.intArg2;
								@Pc(573) int local573 = req.intArg;
								this.customCursor.mouseMove(local573, local570);
							} else if (type == 15) {
								@Pc(591) boolean local591 = req.intArg != 0;
								@Pc(595) Component local595 = (Component) req.objArg;
								this.customCursor.setComponent(local595, local591);
							} else if (type == 17) {
								local687 = (Object[]) req.objArg;
								this.customCursor.setCursor((Point) local687[2], req.intArg, (Component) local687[0], req.intArg2, (int[]) local687[1]);
							} else if (type == 16) {
								try {
									if (!osNameLower.startsWith("win")) {
										throw new Exception();
									}
									local147 = (String) req.objArg;
									if (!local147.startsWith("http://") && !local147.startsWith("https://")) {
										throw new Exception();
									}
									@Pc(636) String local636 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789?&=,.%+-_#:/*";
									for (local490 = 0; local490 < local147.length(); local490++) {
										if (local636.indexOf(local147.charAt(local490)) == -1) {
											throw new Exception();
										}
									}
									Runtime.getRuntime().exec("cmd /c start \"j\" \"" + local147 + "\"");
									req.result = null;
								} catch (@Pc(674) Exception local674) {
									req.result = local674;
								}
							} else {
								throw new Exception();
							}
						}
					}
				}
				req.status = 1;
			} catch (@Pc(830) ThreadDeath local830) {
				throw local830;
			} catch (@Pc(833) Throwable local833) {
				req.status = 2;
			}
		}
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(ILjava/lang/Class;)Lsignlink!im;")
	public final PrivilegedRequest method5123(@OriginalArg(1) Class arg0) {
		return this.newRequest(10, 0, arg0, 0);
	}

	@OriginalMember(owner = "signlink!ll", name = "b", descriptor = "(I)V")
	public final void close() {
		synchronized (this) {
			this.isClosed = true;
			this.notifyAll();
		}
		try {
			this.thread.join();
		} catch (@Pc(21) InterruptedException local21) {
		}
		if (this.cacheDat != null) {
			try {
				this.cacheDat.method5136();
			} catch (@Pc(39) IOException local39) {
			}
		}
		if (this.masterIndex != null) {
			try {
				this.masterIndex.method5136();
			} catch (@Pc(49) IOException local49) {
			}
		}
		if (this.cacheIndex != null) {
			for (@Pc(55) int local55 = 0; local55 < this.cacheIndex.length; local55++) {
				if (this.cacheIndex[local55] != null) {
					try {
						this.cacheIndex[local55].method5136();
					} catch (@Pc(79) IOException local79) {
					}
				}
			}
		}
		if (this.uidDat != null) {
			try {
				this.uidDat.method5136();
			} catch (@Pc(93) IOException local93) {
			}
		}
	}

	@OriginalMember(owner = "signlink!ll", name = "b", descriptor = "(B)Lsignlink!ai;")
	public final AudioSource method5125() {
		return this.anInterface10_2;
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(ILjava/lang/String;Ljava/lang/Class;)Lsignlink!im;")
	public final PrivilegedRequest method5126(@OriginalArg(1) String arg0, @OriginalArg(2) Class arg1) {
		return this.newRequest(9, 0, new Object[] { arg1, arg0 }, 0);
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(II)Lsignlink!im;")
	public final PrivilegedRequest method5128(@OriginalArg(1) int arg0) {
		return this.newRequest(3, 0, null, arg0);
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(IIIII)Lsignlink!im;")
	public final PrivilegedRequest method5129(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
		return this.newRequest(6, arg0 << 16, null, (arg2 << 16) + arg1);
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(IILjava/lang/Runnable;)Lsignlink!im;")
	public final PrivilegedRequest threadreq(@OriginalArg(1) int arg0, @OriginalArg(2) Runnable arg1) {
		return this.newRequest(2, 0, arg1, arg0);
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(Ljava/lang/String;Z)Lsignlink!im;")
	public final PrivilegedRequest method5131(@OriginalArg(0) String arg0) {
		return this.newRequest(16, 0, arg0, 0);
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(B)Lsignlink!im;")
	public final PrivilegedRequest method5132() {
		return this.newRequest(5, 0, null, 0);
	}
}
