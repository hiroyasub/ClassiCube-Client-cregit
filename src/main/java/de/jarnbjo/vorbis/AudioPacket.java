begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * $ProjectName$  * $ProjectRevision$  * -----------------------------------------------------------  * $Id: AudioPacket.java,v 1.2 2003/03/16 01:11:12 jarnbjo Exp $  * -----------------------------------------------------------  *  * $Author: jarnbjo $  *  * Description:  *  * Copyright 2002-2003 Tor-Einar Jarnbjo  * -----------------------------------------------------------  *  * Change History  * -----------------------------------------------------------  * $Log: AudioPacket.java,v $  * Revision 1.2  2003/03/16 01:11:12  jarnbjo  * no message  *  *  */
end_comment

begin_package
package|package
name|de
operator|.
name|jarnbjo
operator|.
name|vorbis
package|;
end_package

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|IOException
import|;
end_import

begin_import
import|import
name|de
operator|.
name|jarnbjo
operator|.
name|util
operator|.
name|io
operator|.
name|*
import|;
end_import

begin_class
class|class
name|AudioPacket
block|{
specifier|private
name|int
name|modeNumber
decl_stmt|;
specifier|private
name|Mode
name|mode
decl_stmt|;
specifier|private
name|Mapping
name|mapping
decl_stmt|;
specifier|private
name|int
name|n
decl_stmt|;
comment|// block size
specifier|private
name|boolean
name|blockFlag
decl_stmt|,
name|previousWindowFlag
decl_stmt|,
name|nextWindowFlag
decl_stmt|;
specifier|private
name|int
name|windowCenter
decl_stmt|,
name|leftWindowStart
decl_stmt|,
name|leftWindowEnd
decl_stmt|,
name|leftN
decl_stmt|,
name|rightWindowStart
decl_stmt|,
name|rightWindowEnd
decl_stmt|,
name|rightN
decl_stmt|;
specifier|private
name|float
index|[]
name|window
decl_stmt|;
specifier|private
name|float
index|[]
index|[]
name|pcm
decl_stmt|;
specifier|private
name|int
index|[]
index|[]
name|pcmInt
decl_stmt|;
specifier|private
name|Floor
index|[]
name|channelFloors
decl_stmt|;
specifier|private
name|boolean
index|[]
name|noResidues
decl_stmt|;
specifier|private
specifier|final
specifier|static
name|float
index|[]
index|[]
name|windows
init|=
operator|new
name|float
index|[
literal|8
index|]
index|[]
decl_stmt|;
specifier|protected
name|AudioPacket
parameter_list|(
specifier|final
name|VorbisStream
name|vorbis
parameter_list|,
specifier|final
name|BitInputStream
name|source
parameter_list|)
throws|throws
name|VorbisFormatException
throws|,
name|IOException
block|{
specifier|final
name|SetupHeader
name|sHeader
init|=
name|vorbis
operator|.
name|getSetupHeader
argument_list|()
decl_stmt|;
specifier|final
name|IdentificationHeader
name|iHeader
init|=
name|vorbis
operator|.
name|getIdentificationHeader
argument_list|()
decl_stmt|;
specifier|final
name|Mode
index|[]
name|modes
init|=
name|sHeader
operator|.
name|getModes
argument_list|()
decl_stmt|;
specifier|final
name|Mapping
index|[]
name|mappings
init|=
name|sHeader
operator|.
name|getMappings
argument_list|()
decl_stmt|;
specifier|final
name|Residue
index|[]
name|residues
init|=
name|sHeader
operator|.
name|getResidues
argument_list|()
decl_stmt|;
specifier|final
name|int
name|channels
init|=
name|iHeader
operator|.
name|getChannels
argument_list|()
decl_stmt|;
if|if
condition|(
name|source
operator|.
name|getInt
argument_list|(
literal|1
argument_list|)
operator|!=
literal|0
condition|)
block|{
throw|throw
operator|new
name|VorbisFormatException
argument_list|(
literal|"Packet type mismatch when trying to create an audio packet."
argument_list|)
throw|;
block|}
name|modeNumber
operator|=
name|source
operator|.
name|getInt
argument_list|(
name|Util
operator|.
name|ilog
argument_list|(
name|modes
operator|.
name|length
operator|-
literal|1
argument_list|)
argument_list|)
expr_stmt|;
try|try
block|{
name|mode
operator|=
name|modes
index|[
name|modeNumber
index|]
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|ArrayIndexOutOfBoundsException
name|e
parameter_list|)
block|{
throw|throw
operator|new
name|VorbisFormatException
argument_list|(
literal|"Reference to invalid mode in audio packet."
argument_list|)
throw|;
block|}
name|mapping
operator|=
name|mappings
index|[
name|mode
operator|.
name|getMapping
argument_list|()
index|]
expr_stmt|;
specifier|final
name|int
index|[]
name|magnitudes
init|=
name|mapping
operator|.
name|getMagnitudes
argument_list|()
decl_stmt|;
specifier|final
name|int
index|[]
name|angles
init|=
name|mapping
operator|.
name|getAngles
argument_list|()
decl_stmt|;
name|blockFlag
operator|=
name|mode
operator|.
name|getBlockFlag
argument_list|()
expr_stmt|;
specifier|final
name|int
name|blockSize0
init|=
name|iHeader
operator|.
name|getBlockSize0
argument_list|()
decl_stmt|;
specifier|final
name|int
name|blockSize1
init|=
name|iHeader
operator|.
name|getBlockSize1
argument_list|()
decl_stmt|;
name|n
operator|=
name|blockFlag
condition|?
name|blockSize1
else|:
name|blockSize0
expr_stmt|;
if|if
condition|(
name|blockFlag
condition|)
block|{
name|previousWindowFlag
operator|=
name|source
operator|.
name|getBit
argument_list|()
expr_stmt|;
name|nextWindowFlag
operator|=
name|source
operator|.
name|getBit
argument_list|()
expr_stmt|;
block|}
name|windowCenter
operator|=
name|n
operator|/
literal|2
expr_stmt|;
if|if
condition|(
name|blockFlag
operator|&&
operator|!
name|previousWindowFlag
condition|)
block|{
name|leftWindowStart
operator|=
name|n
operator|/
literal|4
operator|-
name|blockSize0
operator|/
literal|4
expr_stmt|;
name|leftWindowEnd
operator|=
name|n
operator|/
literal|4
operator|+
name|blockSize0
operator|/
literal|4
expr_stmt|;
name|leftN
operator|=
name|blockSize0
operator|/
literal|2
expr_stmt|;
block|}
else|else
block|{
name|leftWindowStart
operator|=
literal|0
expr_stmt|;
name|leftWindowEnd
operator|=
name|n
operator|/
literal|2
expr_stmt|;
name|leftN
operator|=
name|windowCenter
expr_stmt|;
block|}
if|if
condition|(
name|blockFlag
operator|&&
operator|!
name|nextWindowFlag
condition|)
block|{
name|rightWindowStart
operator|=
name|n
operator|*
literal|3
operator|/
literal|4
operator|-
name|blockSize0
operator|/
literal|4
expr_stmt|;
name|rightWindowEnd
operator|=
name|n
operator|*
literal|3
operator|/
literal|4
operator|+
name|blockSize0
operator|/
literal|4
expr_stmt|;
name|rightN
operator|=
name|blockSize0
operator|/
literal|2
expr_stmt|;
block|}
else|else
block|{
name|rightWindowStart
operator|=
name|windowCenter
expr_stmt|;
name|rightWindowEnd
operator|=
name|n
expr_stmt|;
name|rightN
operator|=
name|n
operator|/
literal|2
expr_stmt|;
block|}
name|window
operator|=
name|getComputedWindow
argument_list|()
expr_stmt|;
comment|// new double[n];
name|channelFloors
operator|=
operator|new
name|Floor
index|[
name|channels
index|]
expr_stmt|;
name|noResidues
operator|=
operator|new
name|boolean
index|[
name|channels
index|]
expr_stmt|;
name|pcm
operator|=
operator|new
name|float
index|[
name|channels
index|]
index|[
name|n
index|]
expr_stmt|;
name|pcmInt
operator|=
operator|new
name|int
index|[
name|channels
index|]
index|[
name|n
index|]
expr_stmt|;
name|boolean
name|allFloorsEmpty
init|=
literal|true
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|channels
condition|;
name|i
operator|++
control|)
block|{
name|int
name|submapNumber
init|=
name|mapping
operator|.
name|getMux
argument_list|()
index|[
name|i
index|]
decl_stmt|;
name|int
name|floorNumber
init|=
name|mapping
operator|.
name|getSubmapFloors
argument_list|()
index|[
name|submapNumber
index|]
decl_stmt|;
name|Floor
name|decodedFloor
init|=
name|sHeader
operator|.
name|getFloors
argument_list|()
index|[
name|floorNumber
index|]
operator|.
name|decodeFloor
argument_list|(
name|vorbis
argument_list|,
name|source
argument_list|)
decl_stmt|;
name|channelFloors
index|[
name|i
index|]
operator|=
name|decodedFloor
expr_stmt|;
name|noResidues
index|[
name|i
index|]
operator|=
name|decodedFloor
operator|==
literal|null
expr_stmt|;
if|if
condition|(
name|decodedFloor
operator|!=
literal|null
condition|)
block|{
name|allFloorsEmpty
operator|=
literal|false
expr_stmt|;
block|}
block|}
if|if
condition|(
name|allFloorsEmpty
condition|)
block|{
return|return;
block|}
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|magnitudes
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
operator|!
name|noResidues
index|[
name|magnitudes
index|[
name|i
index|]
index|]
operator|||
operator|!
name|noResidues
index|[
name|angles
index|[
name|i
index|]
index|]
condition|)
block|{
name|noResidues
index|[
name|magnitudes
index|[
name|i
index|]
index|]
operator|=
literal|false
expr_stmt|;
name|noResidues
index|[
name|angles
index|[
name|i
index|]
index|]
operator|=
literal|false
expr_stmt|;
block|}
block|}
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|mapping
operator|.
name|getSubmaps
argument_list|()
condition|;
name|i
operator|++
control|)
block|{
name|int
name|ch
init|=
literal|0
decl_stmt|;
name|boolean
index|[]
name|doNotDecodeFlags
init|=
operator|new
name|boolean
index|[
name|channels
index|]
decl_stmt|;
for|for
control|(
name|int
name|j
init|=
literal|0
init|;
name|j
operator|<
name|channels
condition|;
name|j
operator|++
control|)
block|{
if|if
condition|(
name|mapping
operator|.
name|getMux
argument_list|()
index|[
name|j
index|]
operator|==
name|i
condition|)
block|{
name|doNotDecodeFlags
index|[
name|ch
operator|++
index|]
operator|=
name|noResidues
index|[
name|j
index|]
expr_stmt|;
block|}
block|}
name|int
name|residueNumber
init|=
name|mapping
operator|.
name|getSubmapResidues
argument_list|()
index|[
name|i
index|]
decl_stmt|;
name|Residue
name|residue
init|=
name|residues
index|[
name|residueNumber
index|]
decl_stmt|;
name|residue
operator|.
name|decodeResidue
argument_list|(
name|vorbis
argument_list|,
name|source
argument_list|,
name|mode
argument_list|,
name|ch
argument_list|,
name|doNotDecodeFlags
argument_list|,
name|pcm
argument_list|)
expr_stmt|;
block|}
for|for
control|(
name|int
name|i
init|=
name|mapping
operator|.
name|getCouplingSteps
argument_list|()
operator|-
literal|1
init|;
name|i
operator|>=
literal|0
condition|;
name|i
operator|--
control|)
block|{
specifier|final
name|float
index|[]
name|magnitudeVector
init|=
name|pcm
index|[
name|magnitudes
index|[
name|i
index|]
index|]
decl_stmt|;
specifier|final
name|float
index|[]
name|angleVector
init|=
name|pcm
index|[
name|angles
index|[
name|i
index|]
index|]
decl_stmt|;
for|for
control|(
name|int
name|j
init|=
literal|0
init|;
name|j
operator|<
name|magnitudeVector
operator|.
name|length
condition|;
name|j
operator|++
control|)
block|{
name|float
name|a
init|=
name|angleVector
index|[
name|j
index|]
decl_stmt|;
name|float
name|m
init|=
name|magnitudeVector
index|[
name|j
index|]
decl_stmt|;
if|if
condition|(
name|a
operator|>
literal|0
condition|)
block|{
comment|// magnitudeVector[j]=m;
name|angleVector
index|[
name|j
index|]
operator|=
name|m
operator|>
literal|0
condition|?
name|m
operator|-
name|a
else|:
name|m
operator|+
name|a
expr_stmt|;
block|}
else|else
block|{
name|magnitudeVector
index|[
name|j
index|]
operator|=
name|m
operator|>
literal|0
condition|?
name|m
operator|+
name|a
else|:
name|m
operator|-
name|a
expr_stmt|;
name|angleVector
index|[
name|j
index|]
operator|=
name|m
expr_stmt|;
block|}
block|}
block|}
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|channels
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
name|channelFloors
index|[
name|i
index|]
operator|!=
literal|null
condition|)
block|{
name|channelFloors
index|[
name|i
index|]
operator|.
name|computeFloor
argument_list|(
name|pcm
index|[
name|i
index|]
argument_list|)
expr_stmt|;
block|}
block|}
comment|// perform an inverse mdct to all channels
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|channels
condition|;
name|i
operator|++
control|)
block|{
name|MdctFloat
name|mdct
init|=
name|blockFlag
condition|?
name|iHeader
operator|.
name|getMdct1
argument_list|()
else|:
name|iHeader
operator|.
name|getMdct0
argument_list|()
decl_stmt|;
name|mdct
operator|.
name|imdct
argument_list|(
name|pcm
index|[
name|i
index|]
argument_list|,
name|window
argument_list|,
name|pcmInt
index|[
name|i
index|]
argument_list|)
expr_stmt|;
block|}
block|}
specifier|private
name|float
index|[]
name|getComputedWindow
parameter_list|()
block|{
name|int
name|ix
init|=
operator|(
name|blockFlag
condition|?
literal|4
else|:
literal|0
operator|)
operator|+
operator|(
name|previousWindowFlag
condition|?
literal|2
else|:
literal|0
operator|)
operator|+
operator|(
name|nextWindowFlag
condition|?
literal|1
else|:
literal|0
operator|)
decl_stmt|;
name|float
index|[]
name|w
init|=
name|windows
index|[
name|ix
index|]
decl_stmt|;
if|if
condition|(
name|w
operator|==
literal|null
condition|)
block|{
name|w
operator|=
operator|new
name|float
index|[
name|n
index|]
expr_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|leftN
condition|;
name|i
operator|++
control|)
block|{
name|float
name|x
init|=
operator|(
name|float
operator|)
operator|(
operator|(
name|i
operator|+
literal|.5
operator|)
operator|/
name|leftN
operator|*
name|Math
operator|.
name|PI
operator|/
literal|2.
operator|)
decl_stmt|;
name|x
operator|=
operator|(
name|float
operator|)
name|Math
operator|.
name|sin
argument_list|(
name|x
argument_list|)
expr_stmt|;
name|x
operator|*=
name|x
expr_stmt|;
name|x
operator|*=
operator|(
name|float
operator|)
name|Math
operator|.
name|PI
operator|/
literal|2.
expr_stmt|;
name|x
operator|=
operator|(
name|float
operator|)
name|Math
operator|.
name|sin
argument_list|(
name|x
argument_list|)
expr_stmt|;
name|w
index|[
name|i
operator|+
name|leftWindowStart
index|]
operator|=
name|x
expr_stmt|;
block|}
for|for
control|(
name|int
name|i
init|=
name|leftWindowEnd
init|;
name|i
operator|<
name|rightWindowStart
condition|;
name|w
index|[
name|i
operator|++
index|]
operator|=
literal|1.0f
control|)
empty_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|rightN
condition|;
name|i
operator|++
control|)
block|{
name|float
name|x
init|=
operator|(
name|float
operator|)
operator|(
operator|(
name|rightN
operator|-
name|i
operator|-
literal|.5
operator|)
operator|/
name|rightN
operator|*
name|Math
operator|.
name|PI
operator|/
literal|2.
operator|)
decl_stmt|;
name|x
operator|=
operator|(
name|float
operator|)
name|Math
operator|.
name|sin
argument_list|(
name|x
argument_list|)
expr_stmt|;
name|x
operator|*=
name|x
expr_stmt|;
name|x
operator|*=
operator|(
name|float
operator|)
name|Math
operator|.
name|PI
operator|/
literal|2.
expr_stmt|;
name|x
operator|=
operator|(
name|float
operator|)
name|Math
operator|.
name|sin
argument_list|(
name|x
argument_list|)
expr_stmt|;
name|w
index|[
name|i
operator|+
name|rightWindowStart
index|]
operator|=
name|x
expr_stmt|;
block|}
name|windows
index|[
name|ix
index|]
operator|=
name|w
expr_stmt|;
block|}
return|return
name|w
return|;
block|}
specifier|public
name|float
index|[]
index|[]
name|getFreqencyDomain
parameter_list|()
block|{
return|return
name|pcm
return|;
block|}
specifier|protected
name|int
name|getLeftWindowEnd
parameter_list|()
block|{
return|return
name|leftWindowEnd
return|;
block|}
specifier|protected
name|int
name|getLeftWindowStart
parameter_list|()
block|{
return|return
name|leftWindowStart
return|;
block|}
specifier|protected
name|int
name|getNumberOfSamples
parameter_list|()
block|{
return|return
name|rightWindowStart
operator|-
name|leftWindowStart
return|;
block|}
specifier|public
name|int
index|[]
index|[]
name|getPcm
parameter_list|()
block|{
return|return
name|pcmInt
return|;
block|}
specifier|protected
name|void
name|getPcm
parameter_list|(
specifier|final
name|AudioPacket
name|previousPacket
parameter_list|,
specifier|final
name|byte
index|[]
name|buffer
parameter_list|)
block|{
name|int
name|channels
init|=
name|pcm
operator|.
name|length
decl_stmt|;
name|int
name|val
decl_stmt|;
comment|// copy left window flank and mix with right window flank from
comment|// the previous audio packet
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|channels
condition|;
name|i
operator|++
control|)
block|{
name|int
name|ix
init|=
literal|0
decl_stmt|,
name|j2
init|=
name|previousPacket
operator|.
name|rightWindowStart
decl_stmt|;
specifier|final
name|int
index|[]
name|ppcm
init|=
name|previousPacket
operator|.
name|pcmInt
index|[
name|i
index|]
decl_stmt|;
specifier|final
name|int
index|[]
name|tpcm
init|=
name|pcmInt
index|[
name|i
index|]
decl_stmt|;
for|for
control|(
name|int
name|j
init|=
name|leftWindowStart
init|;
name|j
operator|<
name|leftWindowEnd
condition|;
name|j
operator|++
control|)
block|{
name|val
operator|=
name|ppcm
index|[
name|j2
operator|++
index|]
operator|+
name|tpcm
index|[
name|j
index|]
expr_stmt|;
if|if
condition|(
name|val
operator|>
literal|32767
condition|)
name|val
operator|=
literal|32767
expr_stmt|;
if|if
condition|(
name|val
operator|<
operator|-
literal|32768
condition|)
name|val
operator|=
operator|-
literal|32768
expr_stmt|;
name|buffer
index|[
name|ix
operator|+
operator|(
name|i
operator|*
literal|2
operator|)
operator|+
literal|1
index|]
operator|=
operator|(
name|byte
operator|)
operator|(
name|val
operator|&
literal|0xff
operator|)
expr_stmt|;
name|buffer
index|[
name|ix
operator|+
operator|(
name|i
operator|*
literal|2
operator|)
index|]
operator|=
operator|(
name|byte
operator|)
operator|(
operator|(
name|val
operator|>>
literal|8
operator|)
operator|&
literal|0xff
operator|)
expr_stmt|;
name|ix
operator|+=
name|channels
operator|*
literal|2
expr_stmt|;
block|}
name|ix
operator|=
operator|(
name|leftWindowEnd
operator|-
name|leftWindowStart
operator|)
operator|*
name|channels
operator|*
literal|2
expr_stmt|;
for|for
control|(
name|int
name|j
init|=
name|leftWindowEnd
init|;
name|j
operator|<
name|rightWindowStart
condition|;
name|j
operator|++
control|)
block|{
name|val
operator|=
name|tpcm
index|[
name|j
index|]
expr_stmt|;
if|if
condition|(
name|val
operator|>
literal|32767
condition|)
name|val
operator|=
literal|32767
expr_stmt|;
if|if
condition|(
name|val
operator|<
operator|-
literal|32768
condition|)
name|val
operator|=
operator|-
literal|32768
expr_stmt|;
name|buffer
index|[
name|ix
operator|+
operator|(
name|i
operator|*
literal|2
operator|)
operator|+
literal|1
index|]
operator|=
operator|(
name|byte
operator|)
operator|(
name|val
operator|&
literal|0xff
operator|)
expr_stmt|;
name|buffer
index|[
name|ix
operator|+
operator|(
name|i
operator|*
literal|2
operator|)
index|]
operator|=
operator|(
name|byte
operator|)
operator|(
operator|(
name|val
operator|>>
literal|8
operator|)
operator|&
literal|0xff
operator|)
expr_stmt|;
name|ix
operator|+=
name|channels
operator|*
literal|2
expr_stmt|;
block|}
block|}
block|}
specifier|protected
name|int
name|getPcm
parameter_list|(
specifier|final
name|AudioPacket
name|previousPacket
parameter_list|,
specifier|final
name|int
index|[]
index|[]
name|buffer
parameter_list|)
block|{
name|int
name|channels
init|=
name|pcm
operator|.
name|length
decl_stmt|;
name|int
name|val
decl_stmt|;
comment|// copy left window flank and mix with right window flank from
comment|// the previous audio packet
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|channels
condition|;
name|i
operator|++
control|)
block|{
name|int
name|j1
init|=
literal|0
decl_stmt|,
name|j2
init|=
name|previousPacket
operator|.
name|rightWindowStart
decl_stmt|;
specifier|final
name|int
index|[]
name|ppcm
init|=
name|previousPacket
operator|.
name|pcmInt
index|[
name|i
index|]
decl_stmt|;
specifier|final
name|int
index|[]
name|tpcm
init|=
name|pcmInt
index|[
name|i
index|]
decl_stmt|;
specifier|final
name|int
index|[]
name|target
init|=
name|buffer
index|[
name|i
index|]
decl_stmt|;
for|for
control|(
name|int
name|j
init|=
name|leftWindowStart
init|;
name|j
operator|<
name|leftWindowEnd
condition|;
name|j
operator|++
control|)
block|{
name|val
operator|=
name|ppcm
index|[
name|j2
operator|++
index|]
operator|+
name|tpcm
index|[
name|j
index|]
expr_stmt|;
if|if
condition|(
name|val
operator|>
literal|32767
condition|)
name|val
operator|=
literal|32767
expr_stmt|;
if|if
condition|(
name|val
operator|<
operator|-
literal|32768
condition|)
name|val
operator|=
operator|-
literal|32768
expr_stmt|;
name|target
index|[
name|j1
operator|++
index|]
operator|=
name|val
expr_stmt|;
block|}
block|}
comment|// use System.arraycopy to copy the middle part (if any)
comment|// of the window
if|if
condition|(
name|leftWindowEnd
operator|+
literal|1
operator|<
name|rightWindowStart
condition|)
block|{
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|channels
condition|;
name|i
operator|++
control|)
block|{
name|System
operator|.
name|arraycopy
argument_list|(
name|pcmInt
index|[
name|i
index|]
argument_list|,
name|leftWindowEnd
argument_list|,
name|buffer
index|[
name|i
index|]
argument_list|,
name|leftWindowEnd
operator|-
name|leftWindowStart
argument_list|,
name|rightWindowStart
operator|-
name|leftWindowEnd
argument_list|)
expr_stmt|;
block|}
block|}
return|return
name|rightWindowStart
operator|-
name|leftWindowStart
return|;
block|}
specifier|protected
name|int
name|getRightWindowEnd
parameter_list|()
block|{
return|return
name|rightWindowEnd
return|;
block|}
specifier|protected
name|int
name|getRightWindowStart
parameter_list|()
block|{
return|return
name|rightWindowStart
return|;
block|}
specifier|protected
name|float
index|[]
name|getWindow
parameter_list|()
block|{
return|return
name|window
return|;
block|}
block|}
end_class

end_unit

