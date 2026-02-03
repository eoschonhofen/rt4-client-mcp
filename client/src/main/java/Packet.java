import java.math.BigInteger;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!wa")
public class Packet extends Linkable {

	@OriginalMember(owner = "client!fi", name = "c", descriptor = "[I")
	public static final int[] crctable = new int[256];

	@OriginalMember(owner = "client!qj", name = "a", descriptor = "[J")
	public static final long[] aLongArray7 = new long[256];

	static {
		for (@Pc(4) int local4 = 0; local4 < 256; local4++) {
			@Pc(9) int local9 = local4;
			for (@Pc(11) int local11 = 0; local11 < 8; local11++) {
				if ((local9 & 0x1) == 1) {
					local9 = local9 >>> 1 ^ 0xEDB88320;
				} else {
					local9 >>>= 0x1;
				}
			}
			crctable[local4] = local9;
		}

		for (@Pc(4) int local4 = 0; local4 < 256; local4++) {
			@Pc(10) long local10 = (long) local4;
			for (@Pc(12) int local12 = 0; local12 < 8; local12++) {
				if ((local10 & 0x1L) == 1L) {
					local10 = local10 >>> 1 ^ 0xC96C5795D7870F42L;
				} else {
					local10 >>>= 0x1;
				}
			}
			aLongArray7[local4] = local10;
		}
	}

	@OriginalMember(owner = "client!wa", name = "y", descriptor = "[B")
	public byte[] data;

	@OriginalMember(owner = "client!wa", name = "T", descriptor = "I")
	public int pos;

	@OriginalMember(owner = "client!wa", name = "<init>", descriptor = "(I)V")
	public Packet(@OriginalArg(0) int arg0) {
		this.data = Static228.method3907(arg0);
		this.pos = 0;
	}

	@OriginalMember(owner = "client!wa", name = "<init>", descriptor = "([B)V")
	public Packet(@OriginalArg(0) byte[] arg0) {
		this.pos = 0;
		this.data = arg0;
	}

	@OriginalMember(owner = "client!nf", name = "a", descriptor = "(II[BB)I")
	public static int getcrc(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) byte[] arg2) {
		@Pc(5) int local5 = -1;
		for (@Pc(15) int local15 = arg0; local15 < arg1; local15++) {
			local5 = local5 >>> 8 ^ crctable[(local5 ^ arg2[local15]) & 0xFF];
		}
		return ~local5;
	}

	@OriginalMember(owner = "client!wa", name = "c", descriptor = "(I)I")
	public final int g2() {
		this.pos += 2;
		return ((this.data[this.pos - 2] & 0xFF) << 8) + (this.data[this.pos - 1] & 0xFF);
	}

	@OriginalMember(owner = "client!wa", name = "b", descriptor = "(II)V")
	public final void p4(@OriginalArg(1) int arg0) {
		this.data[this.pos++] = (byte) (arg0 >> 24);
		this.data[this.pos++] = (byte) (arg0 >> 16);
		this.data[this.pos++] = (byte) (arg0 >> 8);
		this.data[this.pos++] = (byte) arg0;
	}

	@OriginalMember(owner = "client!wa", name = "a", descriptor = "(IIJ)V")
	public final void method2165(@OriginalArg(1) int arg0, @OriginalArg(2) long arg1) {
		@Pc(2) int local2 = arg0 - 1;
		if (local2 < 0 || local2 > 7) {
			throw new IllegalArgumentException();
		}
		for (@Pc(27) int local27 = local2 * 8; local27 >= 0; local27 -= 8) {
			this.data[this.pos++] = (byte) (arg1 >> local27);
		}
	}

	@OriginalMember(owner = "client!wa", name = "a", descriptor = "(JI)V")
	public final void p8(@OriginalArg(0) long arg0) {
		this.data[this.pos++] = (byte) (arg0 >> 56);
		this.data[this.pos++] = (byte) (arg0 >> 48);
		this.data[this.pos++] = (byte) (arg0 >> 40);
		this.data[this.pos++] = (byte) (arg0 >> 32);
		this.data[this.pos++] = (byte) (arg0 >> 24);
		this.data[this.pos++] = (byte) (arg0 >> 16);
		this.data[this.pos++] = (byte) (arg0 >> 8);
		this.data[this.pos++] = (byte) arg0;
	}

	@OriginalMember(owner = "client!wa", name = "d", descriptor = "(B)I")
	public final int method2167() {
		@Pc(12) byte local12 = this.data[this.pos++];
		@Pc(24) int local24 = 0;
		while (local12 < 0) {
			local24 = (local12 & 0x7F | local24) << 7;
			local12 = this.data[this.pos++];
		}
		return local12 | local24;
	}

	@OriginalMember(owner = "client!wa", name = "c", descriptor = "(II)V")
	public final void method2168(@OriginalArg(1) int arg0) {
		this.data[this.pos - arg0 - 4] = (byte) (arg0 >> 24);
		this.data[this.pos - arg0 - 3] = (byte) (arg0 >> 16);
		this.data[this.pos - arg0 - 2] = (byte) (arg0 >> 8);
		this.data[this.pos - arg0 - 1] = (byte) arg0;
	}

	@OriginalMember(owner = "client!wa", name = "d", descriptor = "(II)V")
	public final void method2169(@OriginalArg(1) int arg0) {
		this.data[this.pos++] = (byte) (128 - arg0);
	}

	@OriginalMember(owner = "client!wa", name = "a", descriptor = "(ILclient!na;)V")
	public final void method2171(@OriginalArg(1) JagString arg0) {
		this.pos += arg0.method3160(this.data, this.pos, arg0.length());
		this.data[this.pos++] = 0;
	}

	@OriginalMember(owner = "client!wa", name = "d", descriptor = "(I)I")
	public final int method2173() {
		this.pos += 2;
		@Pc(34) int local34 = ((this.data[this.pos - 2] & 0xFF) << 8) + (this.data[this.pos - 1] - 128 & 0xFF);
		if (local34 > 32767) {
			local34 -= 65536;
		}
		return local34;
	}

	@OriginalMember(owner = "client!wa", name = "e", descriptor = "(I)I")
	public final int g4() {
		this.pos += 4;
		return ((this.data[this.pos - 4] & 0xFF) << 24) + ((this.data[this.pos - 3] & 0xFF) << 16) + ((this.data[this.pos - 2] & 0xFF) << 8) + (this.data[this.pos - 1] & 0xFF);
	}

	@OriginalMember(owner = "client!wa", name = "b", descriptor = "(Z)B")
	public final byte method2175() {
		return (byte) (128 - this.data[this.pos++]);
	}

	@OriginalMember(owner = "client!wa", name = "f", descriptor = "(B)Lclient!na;")
	public final JagString method2176() {
		if (this.data[this.pos] == 0) {
			this.pos++;
			return null;
		} else {
			return this.gjstr();
		}
	}

	@OriginalMember(owner = "client!wa", name = "g", descriptor = "(B)I")
	public final int method2177() {
		return this.data[this.pos++] - 128 & 0xFF;
	}

	@OriginalMember(owner = "client!wa", name = "a", descriptor = "(BI)V")
	public final void p1(@OriginalArg(1) int arg0) {
		this.data[this.pos++] = (byte) arg0;
	}

	@OriginalMember(owner = "client!wa", name = "a", descriptor = "([BIII)V")
	public final void method2179(@OriginalArg(0) byte[] arg0, @OriginalArg(2) int arg1) {
		for (@Pc(7) int local7 = 0; local7 < arg1; local7++) {
			this.data[this.pos++] = arg0[local7];
		}
	}

	@OriginalMember(owner = "client!wa", name = "c", descriptor = "(Z)I")
	public final int method2180() {
		return 128 - this.data[this.pos++] & 0xFF;
	}

	@OriginalMember(owner = "client!wa", name = "h", descriptor = "(B)I")
	public final int method2181() {
		this.pos += 3;
		return ((this.data[this.pos - 2] & 0xFF) << 8) + ((this.data[this.pos - 1] & 0xFF) << 16) + (this.data[this.pos + -3] & 0xFF);
	}

	@OriginalMember(owner = "client!wa", name = "f", descriptor = "(I)J")
	public final long g8() {
		@Pc(11) long local11 = (long) this.g4() & 0xFFFFFFFFL;
		@Pc(18) long local18 = (long) this.g4() & 0xFFFFFFFFL;
		return local18 + (local11 << 32);
	}

	@OriginalMember(owner = "client!wa", name = "e", descriptor = "(II)V")
	public final void method2183(@OriginalArg(0) int arg0) {
		this.data[this.pos++] = (byte) arg0;
		this.data[this.pos++] = (byte) (arg0 >> 8);
		this.data[this.pos++] = (byte) (arg0 >> 16);
		this.data[this.pos++] = (byte) (arg0 >> 24);
	}

	@OriginalMember(owner = "client!wa", name = "g", descriptor = "(I)I")
	public final int method2184() {
		this.pos += 2;
		return (this.data[this.pos - 1] - 128 & 0xFF) + ((this.data[this.pos - 2] & 0xFF) << 8);
	}

	@OriginalMember(owner = "client!wa", name = "f", descriptor = "(II)V")
	public final void method2185(@OriginalArg(1) int arg0) {
		this.data[this.pos++] = (byte) (arg0 >> 16);
		this.data[this.pos++] = (byte) (arg0 >> 24);
		this.data[this.pos++] = (byte) arg0;
		this.data[this.pos++] = (byte) (arg0 >> 8);
	}

	@OriginalMember(owner = "client!wa", name = "d", descriptor = "(Z)B")
	public final byte g1b() {
		return this.data[this.pos++];
	}

	@OriginalMember(owner = "client!wa", name = "h", descriptor = "(I)Lclient!na;")
	public final JagString gjstr2() {
		@Pc(10) byte local10 = this.data[this.pos++];
		if (local10 != 0) {
			throw new IllegalStateException("Bad version number in gjstr2");
		}
		@Pc(32) int local32 = this.pos;
		while (this.data[this.pos++] != 0) {
		}
		return Static10.method346(this.data, this.pos - local32 - 1, local32);
	}

	@OriginalMember(owner = "client!wa", name = "a", descriptor = "(FB)V")
	public final void method2188(@OriginalArg(0) float arg0) {
		@Pc(2) int local2 = Float.floatToRawIntBits(arg0);
		this.data[this.pos++] = (byte) local2;
		this.data[this.pos++] = (byte) (local2 >> 8);
		this.data[this.pos++] = (byte) (local2 >> 16);
		this.data[this.pos++] = (byte) (local2 >> 24);
	}

	@OriginalMember(owner = "client!wa", name = "i", descriptor = "(B)B")
	public final byte method2189() {
		return (byte) -this.data[this.pos++];
	}

	@OriginalMember(owner = "client!wa", name = "a", descriptor = "(II[BB)V")
	public final void method2190(@OriginalArg(1) int arg0, @OriginalArg(2) byte[] arg1) {
		for (@Pc(8) int local8 = 0; local8 < arg0; local8++) {
			arg1[local8] = this.data[this.pos++];
		}
	}

	@OriginalMember(owner = "client!wa", name = "a", descriptor = "(IB)V")
	public final void method2191(@OriginalArg(0) int arg0) {
		this.data[this.pos++] = (byte) (arg0 + 128);
		this.data[this.pos++] = (byte) (arg0 >> 8);
	}

	@OriginalMember(owner = "client!wa", name = "i", descriptor = "(I)I")
	public final int method2192() {
		this.pos += 2;
		return (this.data[this.pos - 2] & 0xFF) + ((this.data[this.pos - 1] & 0xFF) << 8);
	}

	@OriginalMember(owner = "client!wa", name = "g", descriptor = "(II)V")
	public final void method2194(@OriginalArg(1) int arg0) {
		if (arg0 >= 0 && arg0 < 128) {
			this.p1(arg0);
		} else if (arg0 >= 0 && arg0 < 32768) {
			this.p2(arg0 + 32768);
		} else {
			throw new IllegalArgumentException();
		}
	}

	@OriginalMember(owner = "client!wa", name = "b", descriptor = "(BI)V")
	public final void psize1(@OriginalArg(1) int arg0) {
		this.data[this.pos - arg0 - 1] = (byte) arg0;
	}

	@OriginalMember(owner = "client!wa", name = "a", descriptor = "([IIII)V")
	public final void method2196(@OriginalArg(0) int[] arg0, @OriginalArg(3) int arg1) {
		@Pc(6) int local6 = this.pos;
		this.pos = 5;
		@Pc(16) int local16 = (arg1 - 5) / 8;
		for (@Pc(18) int local18 = 0; local18 < local16; local18++) {
			@Pc(23) int local23 = -957401312;
			@Pc(27) int local27 = this.g4();
			@Pc(31) int local31 = this.g4();
			@Pc(33) int local33 = 32;
			while (local33-- > 0) {
				local31 -= arg0[local23 >>> 11 & 0x3] + local23 ^ local27 + (local27 >>> 5 ^ local27 << 4);
				local23 -= -1640531527;
				local27 -= (local31 >>> 5 ^ local31 << 4) + local31 ^ arg0[local23 & 0x3] + local23;
			}
			this.pos -= 8;
			this.p4(local27);
			this.p4(local31);
		}
		this.pos = local6;
	}

	@OriginalMember(owner = "client!wa", name = "h", descriptor = "(II)V")
	public final void method2197(@OriginalArg(1) int arg0) {
		if ((arg0 & 0xFFFFFF80) != 0) {
			if ((-16384 & arg0) != 0) {
				if ((arg0 & 0xFFE00000) != 0) {
					if ((arg0 & 0xF0000000) != 0) {
						this.p1(arg0 >>> 28 | 0x80);
					}
					this.p1(arg0 >>> 21 | 0x80);
				}
				this.p1(arg0 >>> 14 | 0x80);
			}
			this.p1(arg0 >>> 7 | 0x80);
		}
		this.p1(arg0 & 0x7F);
	}

	@OriginalMember(owner = "client!wa", name = "i", descriptor = "(II)J")
	public final long method2198(@OriginalArg(0) int arg0) {
		@Pc(2) int local2 = arg0 - 1;
		if (local2 < 0 || local2 > 7) {
			throw new IllegalArgumentException();
		}
		@Pc(21) long local21 = 0L;
		for (@Pc(25) int local25 = local2 * 8; local25 >= 0; local25 -= 8) {
			local21 |= ((long) this.data[this.pos++] & 0xFFL) << local25;
		}
		return local21;
	}

	@OriginalMember(owner = "client!wa", name = "j", descriptor = "(B)I")
	public final int method2199() {
		@Pc(14) int local14 = this.method2204();
		@Pc(16) int local16 = 0;
		while (local14 == 32767) {
			local14 = this.method2204();
			local16 += 32767;
		}
		return local16 + local14;
	}

	@OriginalMember(owner = "client!wa", name = "a", descriptor = "(II[BI)V")
	public final void method2200(@OriginalArg(1) int arg0, @OriginalArg(2) byte[] arg1) {
		for (@Pc(12) int local12 = arg0 - 1; local12 >= 0; local12--) {
			arg1[local12] = this.data[this.pos++];
		}
	}

	@OriginalMember(owner = "client!wa", name = "j", descriptor = "(II)V")
	public final void method2201(@OriginalArg(0) int arg0) {
		this.data[this.pos++] = (byte) (arg0 >> 8);
		this.data[this.pos++] = (byte) arg0;
		this.data[this.pos++] = (byte) (arg0 >> 24);
		this.data[this.pos++] = (byte) (arg0 >> 16);
	}

	@OriginalMember(owner = "client!wa", name = "e", descriptor = "(Z)Lclient!na;")
	public final JagString gjstr() {
		@Pc(12) int local12 = this.pos;
		while (this.data[this.pos++] != 0) {
		}
		return Static10.method346(this.data, this.pos - local12 - 1, local12);
	}

	@OriginalMember(owner = "client!wa", name = "f", descriptor = "(Z)I")
	public final int method2204() {
		@Pc(17) int local17 = this.data[this.pos] & 0xFF;
		return local17 >= 128 ? this.g2() - 32768 : this.g1();
	}

	@OriginalMember(owner = "client!wa", name = "k", descriptor = "(II)V")
	public final void p3(@OriginalArg(0) int arg0) {
		this.data[this.pos++] = (byte) (arg0 >> 16);
		this.data[this.pos++] = (byte) (arg0 >> 8);
		this.data[this.pos++] = (byte) arg0;
	}

	@OriginalMember(owner = "client!wa", name = "k", descriptor = "(I)I")
	public final int method2206() {
		this.pos += 4;
		return ((this.data[this.pos - 2] & 0xFF) << 24) + ((this.data[this.pos - 1] & 0xFF) << 16) + ((this.data[this.pos + -4] & 0xFF) << 8) + (this.data[this.pos + -3] & 0xFF);
	}

	@OriginalMember(owner = "client!wa", name = "k", descriptor = "(B)I")
	public final int method2207() {
		this.pos += 2;
		return ((this.data[this.pos - 1] & 0xFF) << 8) + (this.data[this.pos - 2] - 128 & 0xFF);
	}

	@OriginalMember(owner = "client!wa", name = "l", descriptor = "(I)I")
	public final int method2208() {
		this.pos += 4;
		return (this.data[this.pos - 4] & 0xFF) + ((this.data[this.pos - 2] & 0xFF) << 16) + ((this.data[this.pos + -1] & 0xFF) << 24) + ((this.data[this.pos + -3] & 0xFF) << 8);
	}

	@OriginalMember(owner = "client!wa", name = "l", descriptor = "(II)V")
	public final void method2209(@OriginalArg(0) int arg0) {
		this.data[this.pos++] = (byte) (arg0 >> 8);
		this.data[this.pos++] = (byte) (arg0 + 128);
	}

	@OriginalMember(owner = "client!wa", name = "b", descriptor = "(IB)V")
	public final void method2211(@OriginalArg(0) int arg0) {
		this.data[this.pos++] = (byte) arg0;
		this.data[this.pos++] = (byte) (arg0 >> 8);
		this.data[this.pos++] = (byte) (arg0 >> 16);
		this.data[this.pos++] = (byte) (arg0 >> 24);
	}

	@OriginalMember(owner = "client!wa", name = "g", descriptor = "(Z)I")
	public final int method2212() {
		return -this.data[this.pos++] & 0xFF;
	}

	@OriginalMember(owner = "client!wa", name = "l", descriptor = "(B)I")
	public final int g2b() {
		this.pos += 2;
		@Pc(27) int local27 = (this.data[this.pos - 1] & 0xFF) + ((this.data[this.pos - 2] & 0xFF) << 8);
		if (local27 > 32767) {
			local27 -= 65536;
		}
		return local27;
	}

	@OriginalMember(owner = "client!wa", name = "m", descriptor = "(I)I")
	public final int method2214() {
		this.pos += 2;
		@Pc(34) int local34 = ((this.data[this.pos - 1] & 0xFF) << 8) + (this.data[this.pos - 2] - 128 & 0xFF);
		if (local34 > 32767) {
			local34 -= 65536;
		}
		return local34;
	}

	@OriginalMember(owner = "client!wa", name = "n", descriptor = "(I)B")
	public final byte method2215() {
		return (byte) (this.data[this.pos++] - 128);
	}

	@OriginalMember(owner = "client!wa", name = "m", descriptor = "(II)V")
	public final void method2216(@OriginalArg(0) int arg0) {
		this.data[this.pos++] = (byte) (arg0 + 128);
	}

	@OriginalMember(owner = "client!wa", name = "m", descriptor = "(B)I")
	public final int method2217() {
		this.pos += 2;
		@Pc(38) int local38 = (this.data[this.pos - 2] & 0xFF) + ((this.data[this.pos - 1] & 0xFF) << 8);
		if (local38 > 32767) {
			local38 -= 65536;
		}
		return local38;
	}

	@OriginalMember(owner = "client!wa", name = "c", descriptor = "(BI)I")
	public final int addcrc(@OriginalArg(1) int arg0) {
		@Pc(16) int local16 = getcrc(arg0, this.pos, this.data);
		this.p4(local16);
		return local16;
	}

	@OriginalMember(owner = "client!wa", name = "n", descriptor = "(B)I")
	public final int g3() {
		this.pos += 3;
		return ((this.data[this.pos - 3] & 0xFF) << 16) + ((this.data[this.pos - 2] << 8 & 0xFF00) + (this.data[this.pos - 1] & 0xFF));
	}

	@OriginalMember(owner = "client!wa", name = "n", descriptor = "(II)V")
	public final void method2222(@OriginalArg(1) int arg0) {
		this.data[this.pos++] = (byte) arg0;
		this.data[this.pos++] = (byte) (arg0 >> 8);
	}

	@OriginalMember(owner = "client!wa", name = "p", descriptor = "(I)I")
	public final int method2223() {
		@Pc(11) int local11 = this.data[this.pos] & 0xFF;
		return local11 < 128 ? this.g1() - 64 : this.g2() - 49152;
	}

	@OriginalMember(owner = "client!wa", name = "o", descriptor = "(B)I")
	public final int method2224() {
		this.pos += 4;
		return ((this.data[this.pos - 3] & 0xFF) << 24) + ((this.data[this.pos - 4] & 0xFF) << 16) + ((this.data[this.pos + -1] & 0xFF) << 8) + (this.data[this.pos + -2] & 0xFF);
	}

	@OriginalMember(owner = "client!wa", name = "a", descriptor = "(Ljava/math/BigInteger;Ljava/math/BigInteger;I)V")
	public final void method2226(@OriginalArg(0) BigInteger arg0, @OriginalArg(1) BigInteger arg1) {
		@Pc(2) int local2 = this.pos;
		this.pos = 0;
		@Pc(8) byte[] local8 = new byte[local2];
		this.method2190(local2, local8);
		@Pc(23) BigInteger local23 = new BigInteger(local8);
		@Pc(28) BigInteger local28 = local23.modPow(arg0, arg1);
		@Pc(38) byte[] local38 = local28.toByteArray();
		this.pos = 0;
		this.p1(local38.length);
		this.method2179(local38, local38.length);
	}

	@OriginalMember(owner = "client!wa", name = "a", descriptor = "(IF)V")
	public final void method2227(@OriginalArg(1) float arg0) {
		@Pc(6) int local6 = Float.floatToRawIntBits(arg0);
		this.data[this.pos++] = (byte) (local6 >> 24);
		this.data[this.pos++] = (byte) (local6 >> 16);
		this.data[this.pos++] = (byte) (local6 >> 8);
		this.data[this.pos++] = (byte) local6;
	}

	@OriginalMember(owner = "client!wa", name = "p", descriptor = "(B)I")
	public final int g1() {
		return this.data[this.pos++] & 0xFF;
	}

	@OriginalMember(owner = "client!wa", name = "o", descriptor = "(II)V")
	public final void p2(@OriginalArg(1) int arg0) {
		this.data[this.pos++] = (byte) (arg0 >> 8);
		this.data[this.pos++] = (byte) arg0;
	}
}
