begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * $ProjectName$  * $ProjectRevision$  * -----------------------------------------------------------  * $Id: Floor.java,v 1.3 2003/04/10 19:49:04 jarnbjo Exp $  * -----------------------------------------------------------  *  * $Author: jarnbjo $  *  * Description:  *  * Copyright 2002-2003 Tor-Einar Jarnbjo  * -----------------------------------------------------------  *  * Change History  * -----------------------------------------------------------  * $Log: Floor.java,v $  * Revision 1.3  2003/04/10 19:49:04  jarnbjo  * no message  *  * Revision 1.2  2003/03/16 01:11:12  jarnbjo  * no message  *  *  */
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
name|de
operator|.
name|jarnbjo
operator|.
name|util
operator|.
name|io
operator|.
name|BitInputStream
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|IOException
import|;
end_import

begin_class
specifier|public
specifier|abstract
class|class
name|Floor
block|{
specifier|public
specifier|final
specifier|static
name|float
index|[]
name|DB_STATIC_TABLE
init|=
block|{
literal|1.0649863e-07f
block|,
literal|1.1341951e-07f
block|,
literal|1.2079015e-07f
block|,
literal|1.2863978e-07f
block|,
literal|1.3699951e-07f
block|,
literal|1.4590251e-07f
block|,
literal|1.5538408e-07f
block|,
literal|1.6548181e-07f
block|,
literal|1.7623575e-07f
block|,
literal|1.8768855e-07f
block|,
literal|1.9988561e-07f
block|,
literal|2.128753e-07f
block|,
literal|2.2670913e-07f
block|,
literal|2.4144197e-07f
block|,
literal|2.5713223e-07f
block|,
literal|2.7384213e-07f
block|,
literal|2.9163793e-07f
block|,
literal|3.1059021e-07f
block|,
literal|3.3077411e-07f
block|,
literal|3.5226968e-07f
block|,
literal|3.7516214e-07f
block|,
literal|3.9954229e-07f
block|,
literal|4.2550680e-07f
block|,
literal|4.5315863e-07f
block|,
literal|4.8260743e-07f
block|,
literal|5.1396998e-07f
block|,
literal|5.4737065e-07f
block|,
literal|5.8294187e-07f
block|,
literal|6.2082472e-07f
block|,
literal|6.6116941e-07f
block|,
literal|7.0413592e-07f
block|,
literal|7.4989464e-07f
block|,
literal|7.9862701e-07f
block|,
literal|8.5052630e-07f
block|,
literal|9.0579828e-07f
block|,
literal|9.6466216e-07f
block|,
literal|1.0273513e-06f
block|,
literal|1.0941144e-06f
block|,
literal|1.1652161e-06f
block|,
literal|1.2409384e-06f
block|,
literal|1.3215816e-06f
block|,
literal|1.4074654e-06f
block|,
literal|1.4989305e-06f
block|,
literal|1.5963394e-06f
block|,
literal|1.7000785e-06f
block|,
literal|1.8105592e-06f
block|,
literal|1.9282195e-06f
block|,
literal|2.0535261e-06f
block|,
literal|2.1869758e-06f
block|,
literal|2.3290978e-06f
block|,
literal|2.4804557e-06f
block|,
literal|2.6416497e-06f
block|,
literal|2.8133190e-06f
block|,
literal|2.9961443e-06f
block|,
literal|3.1908506e-06f
block|,
literal|3.3982101e-06f
block|,
literal|3.6190449e-06f
block|,
literal|3.8542308e-06f
block|,
literal|4.1047004e-06f
block|,
literal|4.3714470e-06f
block|,
literal|4.6555282e-06f
block|,
literal|4.9580707e-06f
block|,
literal|5.2802740e-06f
block|,
literal|5.6234160e-06f
block|,
literal|5.9888572e-06f
block|,
literal|6.3780469e-06f
block|,
literal|6.7925283e-06f
block|,
literal|7.2339451e-06f
block|,
literal|7.7040476e-06f
block|,
literal|8.2047000e-06f
block|,
literal|8.7378876e-06f
block|,
literal|9.3057248e-06f
block|,
literal|9.9104632e-06f
block|,
literal|1.0554501e-05f
block|,
literal|1.1240392e-05f
block|,
literal|1.1970856e-05f
block|,
literal|1.2748789e-05f
block|,
literal|1.3577278e-05f
block|,
literal|1.4459606e-05f
block|,
literal|1.5399272e-05f
block|,
literal|1.6400004e-05f
block|,
literal|1.7465768e-05f
block|,
literal|1.8600792e-05f
block|,
literal|1.9809576e-05f
block|,
literal|2.1096914e-05f
block|,
literal|2.2467911e-05f
block|,
literal|2.3928002e-05f
block|,
literal|2.5482978e-05f
block|,
literal|2.7139006e-05f
block|,
literal|2.8902651e-05f
block|,
literal|3.0780908e-05f
block|,
literal|3.2781225e-05f
block|,
literal|3.4911534e-05f
block|,
literal|3.7180282e-05f
block|,
literal|3.9596466e-05f
block|,
literal|4.2169667e-05f
block|,
literal|4.4910090e-05f
block|,
literal|4.7828601e-05f
block|,
literal|5.0936773e-05f
block|,
literal|5.4246931e-05f
block|,
literal|5.7772202e-05f
block|,
literal|6.1526565e-05f
block|,
literal|6.5524908e-05f
block|,
literal|6.9783085e-05f
block|,
literal|7.4317983e-05f
block|,
literal|7.9147585e-05f
block|,
literal|8.4291040e-05f
block|,
literal|8.9768747e-05f
block|,
literal|9.5602426e-05f
block|,
literal|0.00010181521f
block|,
literal|0.00010843174f
block|,
literal|0.00011547824f
block|,
literal|0.00012298267f
block|,
literal|0.00013097477f
block|,
literal|0.00013948625f
block|,
literal|0.00014855085f
block|,
literal|0.00015820453f
block|,
literal|0.00016848555f
block|,
literal|0.00017943469f
block|,
literal|0.00019109536f
block|,
literal|0.00020351382f
block|,
literal|0.00021673929f
block|,
literal|0.00023082423f
block|,
literal|0.00024582449f
block|,
literal|0.00026179955f
block|,
literal|0.00027881276f
block|,
literal|0.00029693158f
block|,
literal|0.00031622787f
block|,
literal|0.00033677814f
block|,
literal|0.00035866388f
block|,
literal|0.00038197188f
block|,
literal|0.00040679456f
block|,
literal|0.00043323036f
block|,
literal|0.00046138411f
block|,
literal|0.00049136745f
block|,
literal|0.00052329927f
block|,
literal|0.00055730621f
block|,
literal|0.00059352311f
block|,
literal|0.00063209358f
block|,
literal|0.00067317058f
block|,
literal|0.00071691700f
block|,
literal|0.00076350630f
block|,
literal|0.00081312324f
block|,
literal|0.00086596457f
block|,
literal|0.00092223983f
block|,
literal|0.00098217216f
block|,
literal|0.0010459992f
block|,
literal|0.0011139742f
block|,
literal|0.0011863665f
block|,
literal|0.0012634633f
block|,
literal|0.0013455702f
block|,
literal|0.0014330129f
block|,
literal|0.0015261382f
block|,
literal|0.0016253153f
block|,
literal|0.0017309374f
block|,
literal|0.0018434235f
block|,
literal|0.0019632195f
block|,
literal|0.0020908006f
block|,
literal|0.0022266726f
block|,
literal|0.0023713743f
block|,
literal|0.0025254795f
block|,
literal|0.0026895994f
block|,
literal|0.0028643847f
block|,
literal|0.0030505286f
block|,
literal|0.0032487691f
block|,
literal|0.0034598925f
block|,
literal|0.0036847358f
block|,
literal|0.0039241906f
block|,
literal|0.0041792066f
block|,
literal|0.0044507950f
block|,
literal|0.0047400328f
block|,
literal|0.0050480668f
block|,
literal|0.0053761186f
block|,
literal|0.0057254891f
block|,
literal|0.0060975636f
block|,
literal|0.0064938176f
block|,
literal|0.0069158225f
block|,
literal|0.0073652516f
block|,
literal|0.0078438871f
block|,
literal|0.0083536271f
block|,
literal|0.0088964928f
block|,
literal|0.009474637f
block|,
literal|0.010090352f
block|,
literal|0.010746080f
block|,
literal|0.011444421f
block|,
literal|0.012188144f
block|,
literal|0.012980198f
block|,
literal|0.013823725f
block|,
literal|0.014722068f
block|,
literal|0.015678791f
block|,
literal|0.016697687f
block|,
literal|0.017782797f
block|,
literal|0.018938423f
block|,
literal|0.020169149f
block|,
literal|0.021479854f
block|,
literal|0.022875735f
block|,
literal|0.024362330f
block|,
literal|0.025945531f
block|,
literal|0.027631618f
block|,
literal|0.029427276f
block|,
literal|0.031339626f
block|,
literal|0.033376252f
block|,
literal|0.035545228f
block|,
literal|0.037855157f
block|,
literal|0.040315199f
block|,
literal|0.042935108f
block|,
literal|0.045725273f
block|,
literal|0.048696758f
block|,
literal|0.051861348f
block|,
literal|0.055231591f
block|,
literal|0.058820850f
block|,
literal|0.062643361f
block|,
literal|0.066714279f
block|,
literal|0.071049749f
block|,
literal|0.075666962f
block|,
literal|0.080584227f
block|,
literal|0.085821044f
block|,
literal|0.091398179f
block|,
literal|0.097337747f
block|,
literal|0.10366330f
block|,
literal|0.11039993f
block|,
literal|0.11757434f
block|,
literal|0.12521498f
block|,
literal|0.13335215f
block|,
literal|0.14201813f
block|,
literal|0.15124727f
block|,
literal|0.16107617f
block|,
literal|0.17154380f
block|,
literal|0.18269168f
block|,
literal|0.19456402f
block|,
literal|0.20720788f
block|,
literal|0.22067342f
block|,
literal|0.23501402f
block|,
literal|0.25028656f
block|,
literal|0.26655159f
block|,
literal|0.28387361f
block|,
literal|0.30232132f
block|,
literal|0.32196786f
block|,
literal|0.34289114f
block|,
literal|0.36517414f
block|,
literal|0.38890521f
block|,
literal|0.41417847f
block|,
literal|0.44109412f
block|,
literal|0.46975890f
block|,
literal|0.50028648f
block|,
literal|0.53279791f
block|,
literal|0.56742212f
block|,
literal|0.60429640f
block|,
literal|0.64356699f
block|,
literal|0.68538959f
block|,
literal|0.72993007f
block|,
literal|0.77736504f
block|,
literal|0.82788260f
block|,
literal|0.88168307f
block|,
literal|0.9389798f
block|,
literal|1F
block|}
decl_stmt|;
specifier|static
name|Floor
name|createInstance
parameter_list|(
name|BitInputStream
name|source
parameter_list|,
name|SetupHeader
name|header
parameter_list|)
throws|throws
name|IOException
block|{
name|int
name|type
init|=
name|source
operator|.
name|getInt
argument_list|(
literal|16
argument_list|)
decl_stmt|;
switch|switch
condition|(
name|type
condition|)
block|{
case|case
literal|0
case|:
return|return
operator|new
name|Floor0
argument_list|(
name|source
argument_list|,
name|header
argument_list|)
return|;
case|case
literal|1
case|:
return|return
operator|new
name|Floor1
argument_list|(
name|source
argument_list|,
name|header
argument_list|)
return|;
default|default:
throw|throw
operator|new
name|VorbisFormatException
argument_list|(
literal|"Floor type "
operator|+
name|type
operator|+
literal|" is not supported."
argument_list|)
throw|;
block|}
block|}
specifier|abstract
name|void
name|computeFloor
parameter_list|(
name|float
index|[]
name|vector
parameter_list|)
function_decl|;
specifier|abstract
name|Floor
name|decodeFloor
parameter_list|(
name|VorbisStream
name|vorbis
parameter_list|,
name|BitInputStream
name|source
parameter_list|)
throws|throws
name|IOException
function_decl|;
specifier|abstract
name|int
name|getType
parameter_list|()
function_decl|;
block|}
end_class

end_unit

