import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ji")
public final class CodeBook {

	@OriginalMember(owner = "client!ji", name = "f", descriptor = "[I")
	private int[] huffmanTree;

	@OriginalMember(owner = "client!ji", name = "c", descriptor = "I")
	public final int dimensions;

	@OriginalMember(owner = "client!ji", name = "e", descriptor = "I")
	private final int entries;

	@OriginalMember(owner = "client!ji", name = "a", descriptor = "[I")
	private final int[] lengths;

	@OriginalMember(owner = "client!ji", name = "d", descriptor = "[I")
	private int[] multiplicands;

	@OriginalMember(owner = "client!ji", name = "b", descriptor = "[[F")
	private float[][] vqLookup;

	@OriginalMember(owner = "client!ji", name = "<init>", descriptor = "()V")
	public CodeBook() {
		JagVorbis.readBits(24);
		this.dimensions = JagVorbis.readBits(16);
		this.entries = JagVorbis.readBits(24);
		this.lengths = new int[this.entries];
		@Pc(23) boolean local23 = JagVorbis.readBit() != 0;
		@Pc(27) int local27;
		@Pc(32) int local32;
		@Pc(46) int local46;
		if (local23) {
			local27 = 0;
			local32 = JagVorbis.readBits(5) + 1;
			while (local27 < this.entries) {
				@Pc(44) int local44 = JagVorbis.readBits(MathTool.bitsRequired(this.entries - local27));
				for (local46 = 0; local46 < local44; local46++) {
					this.lengths[local27++] = local32;
				}
				local32++;
			}
		} else {
			@Pc(66) boolean local66 = JagVorbis.readBit() != 0;
			for (local32 = 0; local32 < this.entries; local32++) {
				if (local66 && JagVorbis.readBit() == 0) {
					this.lengths[local32] = 0;
				} else {
					this.lengths[local32] = JagVorbis.readBits(5) + 1;
				}
			}
		}
		this.prepareHuffman();
		local27 = JagVorbis.readBits(4);
		if (local27 > 0) {
			@Pc(103) float local103 = JagVorbis.float32Unpack(JagVorbis.readBits(32));
			@Pc(107) float local107 = JagVorbis.float32Unpack(JagVorbis.readBits(32));
			local46 = JagVorbis.readBits(4) + 1;
			@Pc(118) boolean local118 = JagVorbis.readBit() != 0;
			@Pc(127) int local127;
			if (local27 == 1) {
				local127 = lookup1Values(this.entries, this.dimensions);
			} else {
				local127 = this.entries * this.dimensions;
			}
			this.multiplicands = new int[local127];
			@Pc(140) int local140;
			for (local140 = 0; local140 < local127; local140++) {
				this.multiplicands[local140] = JagVorbis.readBits(local46);
			}
			this.vqLookup = new float[this.entries][this.dimensions];
			@Pc(169) float local169;
			@Pc(171) int local171;
			@Pc(173) int local173;
			if (local27 == 1) {
				for (local140 = 0; local140 < this.entries; local140++) {
					local169 = 0.0F;
					local171 = 1;
					for (local173 = 0; local173 < this.dimensions; local173++) {
						@Pc(183) int local183 = local140 / local171 % local127;
						@Pc(195) float local195 = (float) this.multiplicands[local183] * local107 + local103 + local169;
						this.vqLookup[local140][local173] = local195;
						if (local118) {
							local169 = local195;
						}
						local171 *= local127;
					}
				}
			} else {
				for (local140 = 0; local140 < this.entries; local140++) {
					local169 = 0.0F;
					local171 = local140 * this.dimensions;
					for (local173 = 0; local173 < this.dimensions; local173++) {
						@Pc(246) float local246 = (float) this.multiplicands[local171] * local107 + local103 + local169;
						this.vqLookup[local140][local173] = local246;
						if (local118) {
							local169 = local246;
						}
						local171++;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!ji", name = "a", descriptor = "(II)I")
	public static int lookup1Values(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(10) int local10;
		for (local10 = (int) Math.pow((double) arg0, 1.0D / (double) arg1) + 1; Static209.method3709(arg1, local10) > arg0; local10--) {
		}
		return local10;
	}

	@OriginalMember(owner = "client!ji", name = "a", descriptor = "()[F")
	public final float[] method2413() {
		return this.vqLookup[this.method2415()];
	}

	@OriginalMember(owner = "client!ji", name = "b", descriptor = "()V")
	private void prepareHuffman() {
		@Pc(3) int[] local3 = new int[this.entries];
		@Pc(6) int[] local6 = new int[33];
		@Pc(8) int local8;
		@Pc(17) int local17;
		@Pc(26) int local26;
		@Pc(30) int local30;
		@Pc(44) int local44;
		@Pc(53) int local53;
		@Pc(69) int local69;
		for (local8 = 0; local8 < this.entries; local8++) {
			local17 = this.lengths[local8];
			if (local17 != 0) {
				local26 = 0x1 << 32 - local17;
				local30 = local6[local17];
				local3[local8] = local30;
				@Pc(60) int local60;
				if ((local30 & local26) == 0) {
					local44 = local30 | local26;
					for (local53 = local17 - 1; local53 >= 1; local53--) {
						local60 = local6[local53];
						if (local60 != local30) {
							break;
						}
						local69 = 0x1 << 32 - local53;
						if ((local60 & local69) != 0) {
							local6[local53] = local6[local53 - 1];
							break;
						}
						local6[local53] = local60 | local69;
					}
				} else {
					local44 = local6[local17 - 1];
				}
				local6[local17] = local44;
				for (local53 = local17 + 1; local53 <= 32; local53++) {
					local60 = local6[local53];
					if (local60 == local30) {
						local6[local53] = local44;
					}
				}
			}
		}
		this.huffmanTree = new int[8];
		@Pc(122) int local122 = 0;
		for (local8 = 0; local8 < this.entries; local8++) {
			local17 = this.lengths[local8];
			if (local17 != 0) {
				local26 = local3[local8];
				local30 = 0;
				for (local44 = 0; local44 < local17; local44++) {
					local53 = Integer.MIN_VALUE >>> local44;
					if ((local26 & local53) == 0) {
						local30++;
					} else {
						if (this.huffmanTree[local30] == 0) {
							this.huffmanTree[local30] = local122;
						}
						local30 = this.huffmanTree[local30];
					}
					if (local30 >= this.huffmanTree.length) {
						@Pc(184) int[] local184 = new int[this.huffmanTree.length * 2];
						for (local69 = 0; local69 < this.huffmanTree.length; local69++) {
							local184[local69] = this.huffmanTree[local69];
						}
						this.huffmanTree = local184;
					}
				}
				this.huffmanTree[local30] = ~local8;
				if (local30 >= local122) {
					local122 = local30 + 1;
				}
			}
		}
	}

	@OriginalMember(owner = "client!ji", name = "c", descriptor = "()I")
	public final int method2415() {
		@Pc(1) int local1;
		for (local1 = 0; this.huffmanTree[local1] >= 0; local1 = JagVorbis.readBit() == 0 ? local1 + 1 : this.huffmanTree[local1]) {
		}
		return ~this.huffmanTree[local1];
	}
}
