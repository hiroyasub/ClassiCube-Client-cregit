begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|render
package|;
end_package

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|GameSettings
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|Minecraft
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|render
operator|.
name|texture
operator|.
name|TextureFX
import|;
end_import

begin_import
import|import
name|org
operator|.
name|lwjgl
operator|.
name|BufferUtils
import|;
end_import

begin_import
import|import
name|org
operator|.
name|lwjgl
operator|.
name|opengl
operator|.
name|*
import|;
end_import

begin_import
import|import
name|javax
operator|.
name|imageio
operator|.
name|ImageIO
import|;
end_import

begin_import
import|import
name|java
operator|.
name|awt
operator|.
name|*
import|;
end_import

begin_import
import|import
name|java
operator|.
name|awt
operator|.
name|image
operator|.
name|BufferedImage
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|File
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

begin_import
import|import
name|java
operator|.
name|nio
operator|.
name|ByteBuffer
import|;
end_import

begin_import
import|import
name|java
operator|.
name|nio
operator|.
name|IntBuffer
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|ArrayList
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|HashMap
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|List
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|zip
operator|.
name|ZipFile
import|;
end_import

begin_import
import|import static
name|org
operator|.
name|lwjgl
operator|.
name|opengl
operator|.
name|EXTTextureFilterAnisotropic
operator|.
name|GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT
import|;
end_import

begin_import
import|import static
name|org
operator|.
name|lwjgl
operator|.
name|opengl
operator|.
name|EXTTextureFilterAnisotropic
operator|.
name|GL_TEXTURE_MAX_ANISOTROPY_EXT
import|;
end_import

begin_class
specifier|public
class|class
name|TextureManager
block|{
specifier|public
name|boolean
name|Applet
decl_stmt|;
specifier|public
name|TextureManager
parameter_list|(
name|GameSettings
name|settings
parameter_list|,
name|boolean
name|Applet
parameter_list|)
block|{
name|this
operator|.
name|Applet
operator|=
name|Applet
expr_stmt|;
name|this
operator|.
name|settings
operator|=
name|settings
expr_stmt|;
name|minecraftFolder
operator|=
name|Minecraft
operator|.
name|mcDir
expr_stmt|;
name|texturesFolder
operator|=
operator|new
name|File
argument_list|(
name|minecraftFolder
argument_list|,
literal|"texturepacks"
argument_list|)
expr_stmt|;
if|if
condition|(
operator|!
name|texturesFolder
operator|.
name|exists
argument_list|()
condition|)
block|{
name|texturesFolder
operator|.
name|mkdir
argument_list|()
expr_stmt|;
block|}
block|}
specifier|public
name|HashMap
argument_list|<
name|String
argument_list|,
name|Integer
argument_list|>
name|textures
init|=
operator|new
name|HashMap
argument_list|<
name|String
argument_list|,
name|Integer
argument_list|>
argument_list|()
decl_stmt|;
specifier|public
name|HashMap
argument_list|<
name|Integer
argument_list|,
name|BufferedImage
argument_list|>
name|textureImages
init|=
operator|new
name|HashMap
argument_list|<
name|Integer
argument_list|,
name|BufferedImage
argument_list|>
argument_list|()
decl_stmt|;
specifier|public
name|IntBuffer
name|idBuffer
init|=
name|BufferUtils
operator|.
name|createIntBuffer
argument_list|(
literal|1
argument_list|)
decl_stmt|;
specifier|public
name|ByteBuffer
name|textureBuffer
init|=
name|BufferUtils
operator|.
name|createByteBuffer
argument_list|(
literal|262144
argument_list|)
decl_stmt|;
specifier|public
name|List
argument_list|<
name|TextureFX
argument_list|>
name|animations
init|=
operator|new
name|ArrayList
argument_list|<
name|TextureFX
argument_list|>
argument_list|()
decl_stmt|;
specifier|public
name|GameSettings
name|settings
decl_stmt|;
specifier|public
name|String
name|currentTexturePack
init|=
literal|null
decl_stmt|;
specifier|public
name|File
name|minecraftFolder
decl_stmt|;
specifier|public
name|File
name|texturesFolder
decl_stmt|;
specifier|public
name|int
name|previousMipmapMode
decl_stmt|;
specifier|public
name|int
name|loadTexturePack
parameter_list|(
name|String
name|file
parameter_list|)
throws|throws
name|IOException
block|{
name|int
name|textureID
init|=
literal|0
decl_stmt|;
if|if
condition|(
name|file
operator|.
name|endsWith
argument_list|(
literal|".zip"
argument_list|)
condition|)
block|{
name|ZipFile
name|zip
init|=
operator|new
name|ZipFile
argument_list|(
operator|new
name|File
argument_list|(
name|minecraftFolder
argument_list|,
literal|"texturepacks/"
operator|+
name|file
argument_list|)
argument_list|)
decl_stmt|;
name|String
name|terrainPNG
init|=
literal|"terrain.png"
decl_stmt|;
if|if
condition|(
name|zip
operator|.
name|getEntry
argument_list|(
name|terrainPNG
operator|.
name|startsWith
argument_list|(
literal|"/"
argument_list|)
condition|?
name|terrainPNG
operator|.
name|substring
argument_list|(
literal|1
argument_list|,
name|terrainPNG
operator|.
name|length
argument_list|()
argument_list|)
else|:
name|terrainPNG
argument_list|)
operator|!=
literal|null
condition|)
block|{
name|currentTerrainPng
operator|=
name|ImageIO
operator|.
name|read
argument_list|(
name|zip
operator|.
name|getInputStream
argument_list|(
name|zip
operator|.
name|getEntry
argument_list|(
name|terrainPNG
operator|.
name|startsWith
argument_list|(
literal|"/"
argument_list|)
condition|?
name|terrainPNG
operator|.
name|substring
argument_list|(
literal|1
argument_list|,
name|terrainPNG
operator|.
name|length
argument_list|()
argument_list|)
else|:
name|terrainPNG
argument_list|)
argument_list|)
argument_list|)
expr_stmt|;
block|}
else|else
block|{
try|try
block|{
name|currentTerrainPng
operator|=
name|ImageIO
operator|.
name|read
argument_list|(
name|TextureManager
operator|.
name|class
operator|.
name|getResourceAsStream
argument_list|(
name|terrainPNG
argument_list|)
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|Exception
name|e
parameter_list|)
block|{
name|zip
operator|.
name|close
argument_list|()
expr_stmt|;
return|return
name|textureID
return|;
block|}
block|}
name|zip
operator|.
name|close
argument_list|()
expr_stmt|;
name|currentTexturePack
operator|=
name|minecraftFolder
operator|+
literal|"/texturepacks/"
operator|+
name|file
expr_stmt|;
block|}
return|return
name|textureID
return|;
block|}
name|BufferedImage
name|currentTerrainPng
decl_stmt|;
specifier|public
name|int
name|load
parameter_list|(
name|String
name|file
parameter_list|)
block|{
if|if
condition|(
operator|!
name|Applet
operator|&&
operator|!
name|file
operator|.
name|endsWith
argument_list|(
literal|".zip"
argument_list|)
condition|)
block|{
name|file
operator|=
literal|"/resources"
operator|+
name|file
expr_stmt|;
block|}
if|if
condition|(
name|file
operator|.
name|contains
argument_list|(
literal|"terrain"
argument_list|)
operator|&&
name|textures
operator|.
name|containsKey
argument_list|(
literal|"customTerrain"
argument_list|)
condition|)
block|{
return|return
name|textures
operator|.
name|get
argument_list|(
literal|"customTerrain"
argument_list|)
return|;
block|}
if|if
condition|(
name|file
operator|.
name|contains
argument_list|(
literal|"terrain"
argument_list|)
operator|&&
operator|!
name|textures
operator|.
name|containsKey
argument_list|(
literal|"customTerrain"
argument_list|)
condition|)
block|{
if|if
condition|(
name|currentTerrainPng
operator|!=
literal|null
condition|)
block|{
name|int
name|id
init|=
name|load
argument_list|(
name|currentTerrainPng
argument_list|)
decl_stmt|;
name|textures
operator|.
name|put
argument_list|(
literal|"customTerrain"
argument_list|,
name|id
argument_list|)
expr_stmt|;
return|return
name|id
return|;
block|}
block|}
if|if
condition|(
name|textures
operator|.
name|get
argument_list|(
name|file
argument_list|)
operator|!=
literal|null
condition|)
block|{
return|return
name|textures
operator|.
name|get
argument_list|(
name|file
argument_list|)
return|;
block|}
else|else
block|{
try|try
block|{
name|idBuffer
operator|.
name|clear
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glGenTextures
argument_list|(
name|idBuffer
argument_list|)
expr_stmt|;
name|int
name|textureID
init|=
name|idBuffer
operator|.
name|get
argument_list|(
literal|0
argument_list|)
decl_stmt|;
if|if
condition|(
name|file
operator|.
name|endsWith
argument_list|(
literal|".png"
argument_list|)
condition|)
block|{
if|if
condition|(
name|file
operator|.
name|startsWith
argument_list|(
literal|"##"
argument_list|)
condition|)
block|{
name|load
argument_list|(
name|load1
argument_list|(
name|ImageIO
operator|.
name|read
argument_list|(
name|TextureManager
operator|.
name|class
operator|.
name|getResourceAsStream
argument_list|(
name|file
operator|.
name|substring
argument_list|(
literal|2
argument_list|)
argument_list|)
argument_list|)
argument_list|)
argument_list|,
name|textureID
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|load
argument_list|(
name|ImageIO
operator|.
name|read
argument_list|(
name|TextureManager
operator|.
name|class
operator|.
name|getResourceAsStream
argument_list|(
name|file
argument_list|)
argument_list|)
argument_list|,
name|textureID
argument_list|)
expr_stmt|;
block|}
name|textures
operator|.
name|put
argument_list|(
name|file
argument_list|,
name|textureID
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|file
operator|.
name|endsWith
argument_list|(
literal|".zip"
argument_list|)
condition|)
block|{
name|ZipFile
name|zip
init|=
operator|new
name|ZipFile
argument_list|(
operator|new
name|File
argument_list|(
name|minecraftFolder
argument_list|,
literal|"texturepacks/"
operator|+
name|file
argument_list|)
argument_list|)
decl_stmt|;
name|String
name|terrainPNG
init|=
literal|"terrain.png"
decl_stmt|;
if|if
condition|(
name|zip
operator|.
name|getEntry
argument_list|(
name|terrainPNG
operator|.
name|startsWith
argument_list|(
literal|"/"
argument_list|)
condition|?
name|terrainPNG
operator|.
name|substring
argument_list|(
literal|1
argument_list|,
name|terrainPNG
operator|.
name|length
argument_list|()
argument_list|)
else|:
name|terrainPNG
argument_list|)
operator|!=
literal|null
condition|)
block|{
name|load
argument_list|(
name|ImageIO
operator|.
name|read
argument_list|(
name|zip
operator|.
name|getInputStream
argument_list|(
name|zip
operator|.
name|getEntry
argument_list|(
name|terrainPNG
operator|.
name|startsWith
argument_list|(
literal|"/"
argument_list|)
condition|?
name|terrainPNG
operator|.
name|substring
argument_list|(
literal|1
argument_list|,
name|terrainPNG
operator|.
name|length
argument_list|()
argument_list|)
else|:
name|terrainPNG
argument_list|)
argument_list|)
argument_list|)
argument_list|,
name|textureID
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|load
argument_list|(
name|ImageIO
operator|.
name|read
argument_list|(
name|TextureManager
operator|.
name|class
operator|.
name|getResourceAsStream
argument_list|(
name|terrainPNG
argument_list|)
argument_list|)
argument_list|,
name|textureID
argument_list|)
expr_stmt|;
block|}
name|zip
operator|.
name|close
argument_list|()
expr_stmt|;
name|currentTexturePack
operator|=
name|file
expr_stmt|;
block|}
return|return
name|textureID
return|;
block|}
catch|catch
parameter_list|(
name|IOException
name|e
parameter_list|)
block|{
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"!!"
argument_list|,
name|e
argument_list|)
throw|;
block|}
block|}
block|}
specifier|public
specifier|static
name|BufferedImage
name|load1
parameter_list|(
name|BufferedImage
name|image
parameter_list|)
block|{
name|int
name|charWidth
init|=
name|image
operator|.
name|getWidth
argument_list|()
operator|/
literal|16
decl_stmt|;
name|BufferedImage
name|image1
init|=
operator|new
name|BufferedImage
argument_list|(
literal|16
argument_list|,
name|image
operator|.
name|getHeight
argument_list|()
operator|*
name|charWidth
argument_list|,
name|BufferedImage
operator|.
name|TYPE_INT_ARGB_PRE
argument_list|)
decl_stmt|;
name|Graphics
name|graphics
init|=
name|image1
operator|.
name|getGraphics
argument_list|()
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
name|charWidth
condition|;
name|i
operator|++
control|)
block|{
name|graphics
operator|.
name|drawImage
argument_list|(
name|image
argument_list|,
operator|-
name|i
operator|<<
literal|4
argument_list|,
name|i
operator|*
name|image
operator|.
name|getHeight
argument_list|()
argument_list|,
literal|null
argument_list|)
expr_stmt|;
block|}
name|graphics
operator|.
name|dispose
argument_list|()
expr_stmt|;
return|return
name|image1
return|;
block|}
specifier|public
name|int
name|load
parameter_list|(
name|BufferedImage
name|image
parameter_list|)
block|{
name|idBuffer
operator|.
name|clear
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glGenTextures
argument_list|(
name|idBuffer
argument_list|)
expr_stmt|;
name|int
name|textureID
init|=
name|idBuffer
operator|.
name|get
argument_list|(
literal|0
argument_list|)
decl_stmt|;
name|load
argument_list|(
name|image
argument_list|,
name|textureID
argument_list|)
expr_stmt|;
name|textureImages
operator|.
name|put
argument_list|(
name|textureID
argument_list|,
name|image
argument_list|)
expr_stmt|;
return|return
name|textureID
return|;
block|}
specifier|public
name|void
name|load
parameter_list|(
name|BufferedImage
name|image
parameter_list|,
name|int
name|textureID
parameter_list|)
block|{
name|GL11
operator|.
name|glBindTexture
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|,
name|textureID
argument_list|)
expr_stmt|;
if|if
condition|(
name|settings
operator|.
name|smoothing
operator|>
literal|0
condition|)
block|{
name|GL11
operator|.
name|glTexParameteri
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|,
name|GL11
operator|.
name|GL_TEXTURE_MIN_FILTER
argument_list|,
name|GL11
operator|.
name|GL_NEAREST_MIPMAP_LINEAR
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glTexParameteri
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|,
name|GL11
operator|.
name|GL_TEXTURE_MAG_FILTER
argument_list|,
name|GL11
operator|.
name|GL_NEAREST
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glTexParameteri
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|,
name|GL12
operator|.
name|GL_TEXTURE_BASE_LEVEL
argument_list|,
literal|0
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glTexParameteri
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|,
name|GL12
operator|.
name|GL_TEXTURE_MAX_LEVEL
argument_list|,
literal|4
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|GL11
operator|.
name|glTexParameteri
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|,
name|GL11
operator|.
name|GL_TEXTURE_MIN_FILTER
argument_list|,
name|GL11
operator|.
name|GL_NEAREST
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glTexParameteri
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|,
name|GL11
operator|.
name|GL_TEXTURE_MAG_FILTER
argument_list|,
name|GL11
operator|.
name|GL_NEAREST
argument_list|)
expr_stmt|;
block|}
comment|// GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE,
comment|// GL11.GL_MODULATE);
name|int
name|width
init|=
name|image
operator|.
name|getWidth
argument_list|()
decl_stmt|;
name|int
name|height
init|=
name|image
operator|.
name|getHeight
argument_list|()
decl_stmt|;
name|int
index|[]
name|pixels
init|=
operator|new
name|int
index|[
name|width
operator|*
name|height
index|]
decl_stmt|;
name|byte
index|[]
name|color
init|=
operator|new
name|byte
index|[
name|width
operator|*
name|height
operator|<<
literal|2
index|]
decl_stmt|;
name|image
operator|.
name|getRGB
argument_list|(
literal|0
argument_list|,
literal|0
argument_list|,
name|width
argument_list|,
name|height
argument_list|,
name|pixels
argument_list|,
literal|0
argument_list|,
name|width
argument_list|)
expr_stmt|;
for|for
control|(
name|int
name|pixel
init|=
literal|0
init|;
name|pixel
operator|<
name|pixels
operator|.
name|length
condition|;
name|pixel
operator|++
control|)
block|{
name|int
name|alpha
init|=
name|pixels
index|[
name|pixel
index|]
operator|>>>
literal|24
decl_stmt|;
name|int
name|red
init|=
name|pixels
index|[
name|pixel
index|]
operator|>>
literal|16
operator|&
literal|0xFF
decl_stmt|;
name|int
name|green
init|=
name|pixels
index|[
name|pixel
index|]
operator|>>
literal|8
operator|&
literal|0xFF
decl_stmt|;
name|int
name|blue
init|=
name|pixels
index|[
name|pixel
index|]
operator|&
literal|0xFF
decl_stmt|;
if|if
condition|(
name|settings
operator|.
name|anaglyph
condition|)
block|{
name|int
name|rgba3D
init|=
operator|(
name|red
operator|*
literal|30
operator|+
name|green
operator|*
literal|59
operator|+
name|blue
operator|*
literal|11
operator|)
operator|/
literal|100
decl_stmt|;
name|green
operator|=
operator|(
name|red
operator|*
literal|30
operator|+
name|green
operator|*
literal|70
operator|)
operator|/
literal|100
expr_stmt|;
name|blue
operator|=
operator|(
name|red
operator|*
literal|30
operator|+
name|blue
operator|*
literal|70
operator|)
operator|/
literal|100
expr_stmt|;
name|red
operator|=
name|rgba3D
expr_stmt|;
block|}
name|color
index|[
name|pixel
operator|<<
literal|2
index|]
operator|=
operator|(
name|byte
operator|)
name|red
expr_stmt|;
name|color
index|[
operator|(
name|pixel
operator|<<
literal|2
operator|)
operator|+
literal|1
index|]
operator|=
operator|(
name|byte
operator|)
name|green
expr_stmt|;
name|color
index|[
operator|(
name|pixel
operator|<<
literal|2
operator|)
operator|+
literal|2
index|]
operator|=
operator|(
name|byte
operator|)
name|blue
expr_stmt|;
name|color
index|[
operator|(
name|pixel
operator|<<
literal|2
operator|)
operator|+
literal|3
index|]
operator|=
operator|(
name|byte
operator|)
name|alpha
expr_stmt|;
block|}
name|textureBuffer
operator|.
name|clear
argument_list|()
expr_stmt|;
name|textureBuffer
operator|.
name|put
argument_list|(
name|color
argument_list|)
expr_stmt|;
name|textureBuffer
operator|.
name|position
argument_list|(
literal|0
argument_list|)
operator|.
name|limit
argument_list|(
name|color
operator|.
name|length
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glTexImage2D
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|,
literal|0
argument_list|,
name|GL11
operator|.
name|GL_RGBA
argument_list|,
name|width
argument_list|,
name|height
argument_list|,
literal|0
argument_list|,
name|GL11
operator|.
name|GL_RGBA
argument_list|,
name|GL11
operator|.
name|GL_UNSIGNED_BYTE
argument_list|,
name|textureBuffer
argument_list|)
expr_stmt|;
if|if
condition|(
name|settings
operator|.
name|smoothing
operator|>
literal|0
condition|)
block|{
if|if
condition|(
name|settings
operator|.
name|smoothing
operator|==
literal|1
condition|)
block|{
name|ContextCapabilities
name|capabilities
init|=
name|GLContext
operator|.
name|getCapabilities
argument_list|()
decl_stmt|;
if|if
condition|(
name|capabilities
operator|.
name|OpenGL30
condition|)
block|{
if|if
condition|(
name|previousMipmapMode
operator|!=
name|settings
operator|.
name|smoothing
condition|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Using OpenGL 3.0 for mipmap generation."
argument_list|)
expr_stmt|;
block|}
name|GL30
operator|.
name|glGenerateMipmap
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|capabilities
operator|.
name|GL_EXT_framebuffer_object
condition|)
block|{
if|if
condition|(
name|previousMipmapMode
operator|!=
name|settings
operator|.
name|smoothing
condition|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Using GL_EXT_framebuffer_object extension for mipmap generation."
argument_list|)
expr_stmt|;
block|}
name|EXTFramebufferObject
operator|.
name|glGenerateMipmapEXT
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|capabilities
operator|.
name|OpenGL14
condition|)
block|{
if|if
condition|(
name|previousMipmapMode
operator|!=
name|settings
operator|.
name|smoothing
condition|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Using OpenGL 1.4 for mipmap generation."
argument_list|)
expr_stmt|;
block|}
name|GL11
operator|.
name|glTexParameteri
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|,
name|GL14
operator|.
name|GL_GENERATE_MIPMAP
argument_list|,
name|GL11
operator|.
name|GL_TRUE
argument_list|)
expr_stmt|;
block|}
block|}
if|else if
condition|(
name|settings
operator|.
name|smoothing
operator|==
literal|2
condition|)
block|{
if|if
condition|(
name|previousMipmapMode
operator|!=
name|settings
operator|.
name|smoothing
condition|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Using custom system for mipmap generation."
argument_list|)
expr_stmt|;
block|}
name|generateMipMaps
argument_list|(
name|textureBuffer
argument_list|,
name|width
argument_list|,
name|height
argument_list|,
literal|false
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|settings
operator|.
name|anisotropic
operator|>
literal|0
condition|)
block|{
name|float
name|max
init|=
name|GL11
operator|.
name|glGetFloat
argument_list|(
name|GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT
argument_list|)
decl_stmt|;
name|GL11
operator|.
name|glTexParameterf
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|,
name|GL_TEXTURE_MAX_ANISOTROPY_EXT
argument_list|,
name|max
argument_list|)
expr_stmt|;
block|}
block|}
name|previousMipmapMode
operator|=
name|settings
operator|.
name|smoothing
expr_stmt|;
block|}
specifier|public
name|void
name|generateMipMaps
parameter_list|(
name|ByteBuffer
name|data
parameter_list|,
name|int
name|width
parameter_list|,
name|int
name|height
parameter_list|,
name|boolean
name|test
parameter_list|)
block|{
name|ByteBuffer
name|mipData
init|=
name|data
decl_stmt|;
for|for
control|(
name|int
name|level
init|=
name|test
condition|?
literal|0
else|:
literal|1
init|;
name|level
operator|<=
literal|4
condition|;
name|level
operator|++
control|)
block|{
name|int
name|parWidth
init|=
name|width
operator|>>
name|level
operator|-
literal|1
decl_stmt|;
name|int
name|mipWidth
init|=
name|width
operator|>>
name|level
decl_stmt|;
name|int
name|mipHeight
init|=
name|height
operator|>>
name|level
decl_stmt|;
if|if
condition|(
name|mipWidth
operator|<=
literal|0
operator|||
name|mipHeight
operator|<=
literal|0
condition|)
block|{
break|break;
block|}
name|ByteBuffer
name|mipData1
init|=
name|BufferUtils
operator|.
name|createByteBuffer
argument_list|(
name|data
operator|.
name|capacity
argument_list|()
argument_list|)
decl_stmt|;
name|mipData1
operator|.
name|clear
argument_list|()
expr_stmt|;
for|for
control|(
name|int
name|mipX
init|=
literal|0
init|;
name|mipX
operator|<
name|mipWidth
condition|;
name|mipX
operator|++
control|)
block|{
for|for
control|(
name|int
name|mipY
init|=
literal|0
init|;
name|mipY
operator|<
name|mipHeight
condition|;
name|mipY
operator|++
control|)
block|{
name|int
name|p1
init|=
name|mipData
operator|.
name|getInt
argument_list|(
operator|(
name|mipX
operator|*
literal|2
operator|+
literal|0
operator|+
operator|(
name|mipY
operator|*
literal|2
operator|+
literal|0
operator|)
operator|*
name|parWidth
operator|)
operator|*
literal|4
argument_list|)
decl_stmt|;
name|int
name|p2
init|=
name|mipData
operator|.
name|getInt
argument_list|(
operator|(
name|mipX
operator|*
literal|2
operator|+
literal|1
operator|+
operator|(
name|mipY
operator|*
literal|2
operator|+
literal|0
operator|)
operator|*
name|parWidth
operator|)
operator|*
literal|4
argument_list|)
decl_stmt|;
name|int
name|p3
init|=
name|mipData
operator|.
name|getInt
argument_list|(
operator|(
name|mipX
operator|*
literal|2
operator|+
literal|1
operator|+
operator|(
name|mipY
operator|*
literal|2
operator|+
literal|1
operator|)
operator|*
name|parWidth
operator|)
operator|*
literal|4
argument_list|)
decl_stmt|;
name|int
name|p4
init|=
name|mipData
operator|.
name|getInt
argument_list|(
operator|(
name|mipX
operator|*
literal|2
operator|+
literal|0
operator|+
operator|(
name|mipY
operator|*
literal|2
operator|+
literal|1
operator|)
operator|*
name|parWidth
operator|)
operator|*
literal|4
argument_list|)
decl_stmt|;
name|int
name|pixel
init|=
name|b
argument_list|(
name|b
argument_list|(
name|p1
argument_list|,
name|p2
argument_list|)
argument_list|,
name|b
argument_list|(
name|p3
argument_list|,
name|p4
argument_list|)
argument_list|)
decl_stmt|;
name|mipData1
operator|.
name|putInt
argument_list|(
operator|(
name|mipX
operator|+
name|mipY
operator|*
name|mipWidth
operator|)
operator|*
literal|4
argument_list|,
name|pixel
argument_list|)
expr_stmt|;
block|}
block|}
name|GL11
operator|.
name|glTexImage2D
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|,
name|level
argument_list|,
name|GL11
operator|.
name|GL_RGBA
argument_list|,
name|mipWidth
argument_list|,
name|mipHeight
argument_list|,
literal|0
argument_list|,
name|GL11
operator|.
name|GL_RGBA
argument_list|,
name|GL11
operator|.
name|GL_UNSIGNED_BYTE
argument_list|,
name|mipData1
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glAlphaFunc
argument_list|(
name|GL11
operator|.
name|GL_GEQUAL
argument_list|,
literal|0.1F
operator|*
name|level
argument_list|)
expr_stmt|;
comment|// Create
comment|// transparency for
comment|// each level.
name|mipData
operator|=
name|mipData1
expr_stmt|;
block|}
block|}
specifier|private
name|int
name|b
parameter_list|(
name|int
name|c1
parameter_list|,
name|int
name|c2
parameter_list|)
block|{
name|int
name|a1
init|=
operator|(
name|c1
operator|&
literal|0xFF000000
operator|)
operator|>>
literal|24
operator|&
literal|0xFF
decl_stmt|;
name|int
name|a2
init|=
operator|(
name|c2
operator|&
literal|0xFF000000
operator|)
operator|>>
literal|24
operator|&
literal|0xFF
decl_stmt|;
name|int
name|ax
init|=
operator|(
name|a1
operator|+
name|a2
operator|)
operator|/
literal|2
decl_stmt|;
if|if
condition|(
name|ax
operator|>
literal|255
condition|)
block|{
name|ax
operator|=
literal|255
expr_stmt|;
block|}
if|if
condition|(
name|a1
operator|+
name|a2
operator|<=
literal|0
condition|)
block|{
name|a1
operator|=
literal|1
expr_stmt|;
name|a2
operator|=
literal|1
expr_stmt|;
name|ax
operator|=
literal|0
expr_stmt|;
block|}
name|int
name|r1
init|=
operator|(
name|c1
operator|>>
literal|16
operator|&
literal|0xFF
operator|)
operator|*
name|a1
decl_stmt|;
name|int
name|g1
init|=
operator|(
name|c1
operator|>>
literal|8
operator|&
literal|0xFF
operator|)
operator|*
name|a1
decl_stmt|;
name|int
name|b1
init|=
operator|(
name|c1
operator|&
literal|0xFF
operator|)
operator|*
name|a1
decl_stmt|;
name|int
name|r2
init|=
operator|(
name|c2
operator|>>
literal|16
operator|&
literal|0xFF
operator|)
operator|*
name|a2
decl_stmt|;
name|int
name|g2
init|=
operator|(
name|c2
operator|>>
literal|8
operator|&
literal|0xFF
operator|)
operator|*
name|a2
decl_stmt|;
name|int
name|b2
init|=
operator|(
name|c2
operator|&
literal|0xFF
operator|)
operator|*
name|a2
decl_stmt|;
name|int
name|rx
init|=
operator|(
name|r1
operator|+
name|r2
operator|)
operator|/
operator|(
name|a1
operator|+
name|a2
operator|)
decl_stmt|;
name|int
name|gx
init|=
operator|(
name|g1
operator|+
name|g2
operator|)
operator|/
operator|(
name|a1
operator|+
name|a2
operator|)
decl_stmt|;
name|int
name|bx
init|=
operator|(
name|b1
operator|+
name|b2
operator|)
operator|/
operator|(
name|a1
operator|+
name|a2
operator|)
decl_stmt|;
return|return
name|ax
operator|<<
literal|24
operator||
name|rx
operator|<<
literal|16
operator||
name|gx
operator|<<
literal|8
operator||
name|bx
return|;
block|}
specifier|public
name|void
name|registerAnimation
parameter_list|(
name|TextureFX
name|FX
parameter_list|)
block|{
name|animations
operator|.
name|add
argument_list|(
name|FX
argument_list|)
expr_stmt|;
name|FX
operator|.
name|animate
argument_list|()
expr_stmt|;
block|}
block|}
end_class

end_unit

