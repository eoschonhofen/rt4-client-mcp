import java.io.IOException;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!jb")
public final class Js5Net {

	@OriginalMember(owner = "client!jb", name = "A", descriptor = "J")
	private long lastTickMs;

	@OriginalMember(owner = "client!jb", name = "B", descriptor = "Lclient!ma;")
	private ClientStream stream;

	@OriginalMember(owner = "client!jb", name = "C", descriptor = "I")
	private int timeoutMs;

	@OriginalMember(owner = "client!jb", name = "J", descriptor = "Lclient!pm;")
	private Js5NetRequest incomingRequest;

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "Lclient!ce;")
	private final LinkList2 pendingUrgentQueue = new LinkList2();

	@OriginalMember(owner = "client!jb", name = "q", descriptor = "Lclient!ce;")
	private final LinkList2 urgentQueue = new LinkList2();

	@OriginalMember(owner = "client!jb", name = "v", descriptor = "Lclient!ce;")
	private final LinkList2 requestQueue = new LinkList2();

	@OriginalMember(owner = "client!jb", name = "z", descriptor = "Lclient!ce;")
	private final LinkList2 prefetchQueue = new LinkList2();

	@OriginalMember(owner = "client!jb", name = "E", descriptor = "Lclient!wa;")
	private final Packet out = new Packet(4);

	@OriginalMember(owner = "client!jb", name = "G", descriptor = "B")
	private byte xorKey = 0;

	@OriginalMember(owner = "client!jb", name = "I", descriptor = "I")
	public volatile int js5Errors = 0;

	@OriginalMember(owner = "client!jb", name = "H", descriptor = "I")
	public volatile int anInt2963 = 0;

	@OriginalMember(owner = "client!jb", name = "F", descriptor = "Lclient!wa;")
	private final Packet incomingTransferHeader = new Packet(8);

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(I)Z")
	public final boolean method2316() {
		return this.method2321() >= 20;
	}

	@OriginalMember(owner = "client!jb", name = "b", descriptor = "(B)Z")
	public final boolean loop() {
		@Pc(19) int local19;
		if (this.stream != null) {
			@Pc(12) long local12 = MonotonicTime.currentTime();
			local19 = (int) (local12 - this.lastTickMs);
			this.lastTickMs = local12;
			if (local19 > 200) {
				local19 = 200;
			}

			this.timeoutMs += local19;
			if (this.timeoutMs > 30000) {
				try {
					this.stream.close();
				} catch (@Pc(43) Exception ignore) {
				}

				this.stream = null;
			}
		}

		if (this.stream == null) {
			return this.method2328() == 0 && this.method2321() == 0;
		}

		try {
			this.stream.method2832();

			@Pc(75) Js5NetRequest local75;
			for (local75 = (Js5NetRequest) this.pendingUrgentQueue.method795(); local75 != null; local75 = (Js5NetRequest) this.pendingUrgentQueue.method797()) {
				this.out.pos = 0;
				this.out.p1(1);
				this.out.p3((int) local75.key2);
				this.stream.write(this.out.data, 4);
				this.urgentQueue.pushFront(local75);
			}

			for (local75 = (Js5NetRequest) this.requestQueue.method795(); local75 != null; local75 = (Js5NetRequest) this.requestQueue.method797()) {
				this.out.pos = 0;
				this.out.p1(0);
				this.out.p3((int) local75.key2);
				this.stream.write(this.out.data, 4);
				this.prefetchQueue.pushFront(local75);
			}

			for (@Pc(172) int local172 = 0; local172 < 100; local172++) {
				local19 = this.stream.available();
				if (local19 < 0) {
					throw new IOException();
				}
				if (local19 == 0) {
					break;
				}

				this.timeoutMs = 0;

				@Pc(196) byte local196 = 0;
				if (this.incomingRequest == null) {
					local196 = 8;
				} else if (this.incomingRequest.anInt4617 == 0) {
					local196 = 1;
				}

				@Pc(228) int local228;
				@Pc(235) int local235;
				@Pc(283) int local283;
				if (local196 <= 0) {
					local228 = this.incomingRequest.header.data.length - this.incomingRequest.aByte16;
					local235 = 512 - this.incomingRequest.anInt4617;
					if (local235 > local228 - this.incomingRequest.header.pos) {
						local235 = local228 - this.incomingRequest.header.pos;
					}
					if (local235 > local19) {
						local235 = local19;
					}
					this.stream.read(this.incomingRequest.header.pos, local235, this.incomingRequest.header.data);
					if (this.xorKey != 0) {
						for (local283 = 0; local283 < local235; local283++) {
							this.incomingRequest.header.data[this.incomingRequest.header.pos + local283] = (byte) (this.incomingRequest.header.data[this.incomingRequest.header.pos + local283] ^ this.xorKey);
						}
					}

					this.incomingRequest.anInt4617 += local235;
					this.incomingRequest.header.pos += local235;

					if (this.incomingRequest.header.pos == local228) {
						this.incomingRequest.unlink2();
						this.incomingRequest.aBoolean226 = false;
						this.incomingRequest = null;
					} else if (this.incomingRequest.anInt4617 == 512) {
						this.incomingRequest.anInt4617 = 0;
					}
				} else {
					local228 = local196 - this.incomingTransferHeader.pos;
					if (local19 < local228) {
						local228 = local19;
					}

					this.stream.read(this.incomingTransferHeader.pos, local228, this.incomingTransferHeader.data);

					if (this.xorKey != 0) {
						for (local235 = 0; local235 < local228; local235++) {
							this.incomingTransferHeader.data[local235 + this.incomingTransferHeader.pos] ^= this.xorKey;
						}
					}

					this.incomingTransferHeader.pos += local228;
					if (this.incomingTransferHeader.pos >= local196) {
						if (this.incomingRequest == null) {
							this.incomingTransferHeader.pos = 0;
							local235 = this.incomingTransferHeader.g1();
							local283 = this.incomingTransferHeader.g2();
							@Pc(471) int local471 = this.incomingTransferHeader.g1();
							@Pc(476) int local476 = this.incomingTransferHeader.g4();
							@Pc(480) int local480 = local471 & 0x7F;
							@Pc(491) boolean local491 = (local471 & 0x80) != 0;
							@Pc(501) long local501 = (long) ((local235 << 16) + local283);
							@Pc(509) Js5NetRequest local509;
							if (local491) {
								for (local509 = (Js5NetRequest) this.prefetchQueue.method795(); local509 != null && local509.key2 != local501; local509 = (Js5NetRequest) this.prefetchQueue.method797()) {
								}
							} else {
								for (local509 = (Js5NetRequest) this.urgentQueue.method795(); local509 != null && local501 != local509.key2; local509 = (Js5NetRequest) this.urgentQueue.method797()) {
								}
							}
							if (local509 == null) {
								throw new IOException();
							}
							@Pc(568) int local568 = local480 == 0 ? 5 : 9;
							this.incomingRequest = local509;

							this.incomingRequest.header = new Packet(local476 + local568 + this.incomingRequest.aByte16);
							this.incomingRequest.header.p1(local480);
							this.incomingRequest.header.p4(local476);
							this.incomingRequest.anInt4617 = 8;

							this.incomingTransferHeader.pos = 0;
						} else if (this.incomingRequest.anInt4617 != 0) {
							throw new IOException();
						} else if (this.incomingTransferHeader.data[0] == -1) {
							this.incomingRequest.anInt4617 = 1;
							this.incomingTransferHeader.pos = 0;
						} else {
							this.incomingRequest = null;
						}
					}
				}
			}
			return true;
		} catch (@Pc(644) IOException ex) {
			try {
				this.stream.close();
			} catch (@Pc(650) Exception ignore) {
			}

			this.anInt2963 = -2;
			this.js5Errors++;
			this.stream = null;
			return this.method2328() == 0 && this.method2321() == 0;
		}
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(Z)V")
	public final void method2319() {
		if (this.stream == null) {
			return;
		}

		try {
			this.out.pos = 0;
			this.out.p1(7);
			this.out.p3(0);
			this.stream.write(this.out.data, 4);
		} catch (@Pc(39) IOException ex) {
			try {
				this.stream.close();
			} catch (@Pc(45) Exception ignore) {
			}

			this.js5Errors++;
			this.anInt2963 = -2;
			this.stream = null;
		}
	}

	@OriginalMember(owner = "client!jb", name = "b", descriptor = "(I)I")
	private int method2321() {
		return this.requestQueue.method793() + this.prefetchQueue.method793();
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(ZZ)V")
	public final void sendLoginLogoutPacket(@OriginalArg(0) boolean loggedIn) {
		if (this.stream == null) {
			return;
		}

		try {
			this.out.pos = 0;
			this.out.p1(loggedIn ? 2 : 3);
			this.out.p3(0);
			this.stream.write(this.out.data, 4);
		} catch (@Pc(42) IOException ex) {
			try {
				this.stream.close();
			} catch (@Pc(48) Exception ignore) {
			}

			this.js5Errors++;
			this.anInt2963 = -2;
			this.stream = null;
		}
	}

	@OriginalMember(owner = "client!jb", name = "c", descriptor = "(I)V")
	public final void method2323() {
		if (this.stream != null) {
			this.stream.method2833();
		}
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(ZLclient!ma;I)V")
	public final void init(@OriginalArg(0) boolean loggedIn, @OriginalArg(1) ClientStream s) {
		if (this.stream != null) {
			try {
				this.stream.close();
			} catch (@Pc(14) Exception ignore) {
			}

			this.stream = null;
		}

		this.stream = s;
		this.method2331();
		this.sendLoginLogoutPacket(loggedIn);

		this.incomingTransferHeader.pos = 0;
		this.incomingRequest = null;

		while (true) {
			@Pc(44) Js5NetRequest local44 = (Js5NetRequest) this.urgentQueue.search();
			if (local44 == null) {
				while (true) {
					local44 = (Js5NetRequest) this.prefetchQueue.search();
					if (local44 == null) {
						if (this.xorKey != 0) {
							try {
								this.out.pos = 0;
								this.out.p1(4);
								this.out.p1(this.xorKey);
								this.out.p2(0);
								this.stream.write(this.out.data, 4);
							} catch (@Pc(107) IOException ex) {
								try {
									this.stream.close();
								} catch (@Pc(113) Exception ignore) {
								}

								this.anInt2963 = -2;
								this.js5Errors++;
								this.stream = null;
							}
						}

						this.timeoutMs = 0;
						this.lastTickMs = MonotonicTime.currentTime();
						return;
					}

					this.requestQueue.pushFront(local44);
				}
			}

			this.pendingUrgentQueue.pushFront(local44);
		}
	}

	@OriginalMember(owner = "client!jb", name = "c", descriptor = "(B)Z")
	public final boolean method2326() {
		return this.method2328() >= 20;
	}

	@OriginalMember(owner = "client!jb", name = "d", descriptor = "(B)V")
	public final void method2327() {
		try {
			this.stream.close();
		} catch (@Pc(17) Exception local17) {
		}

		this.anInt2963 = -1;
		this.xorKey = (byte) (Math.random() * 255.0D + 1.0D);
		this.stream = null;
		this.js5Errors++;
	}

	@OriginalMember(owner = "client!jb", name = "d", descriptor = "(I)I")
	public final int method2328() {
		return this.pendingUrgentQueue.method793() + this.urgentQueue.method793();
	}

	@OriginalMember(owner = "client!jb", name = "b", descriptor = "(Z)V")
	public final void shutdown() {
		if (this.stream != null) {
			this.stream.close();
		}
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(IIBIZ)Lclient!pm;")
	public final Js5NetRequest queueRequest(@OriginalArg(1) int arg0, @OriginalArg(2) byte arg1, @OriginalArg(3) int arg2, @OriginalArg(4) boolean arg3) {
		@Pc(7) Js5NetRequest local7 = new Js5NetRequest();
		@Pc(14) long local14 = (long) (arg2 + (arg0 << 16));
		local7.aBoolean225 = arg3;
		local7.key2 = local14;
		local7.aByte16 = arg1;
		if (arg3) {
			if (this.method2328() >= 20) {
				throw new RuntimeException();
			}
			this.pendingUrgentQueue.pushFront(local7);
		} else if (this.method2321() < 20) {
			this.requestQueue.pushFront(local7);
		} else {
			throw new RuntimeException();
		}
		return local7;
	}

	@OriginalMember(owner = "client!jb", name = "e", descriptor = "(B)V")
	private void method2331() {
		if (this.stream == null) {
			return;
		}
		try {
			this.out.pos = 0;
			this.out.p1(6);
			this.out.p3(3);
			this.stream.write(this.out.data, 4);
		} catch (@Pc(37) IOException local37) {
			try {
				this.stream.close();
			} catch (@Pc(43) Exception local43) {
			}
			this.js5Errors++;
			this.stream = null;
			this.anInt2963 = -2;
		}
	}
}
