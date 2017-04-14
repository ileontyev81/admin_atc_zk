package atc.gui.admin.zk.converter.path;

import atc.gui.admin.zk.service.BrowserHistoryChangedHandler;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@Component("gzipBase64PathBookmarkConverter")
public class SqueezePathBookmarkConverter implements BrowserHistoryChangedHandler.PathBookmarkConverter
{
	
	@Override
	public String toBookmark(String path) throws Exception
	{
		return java.net.URLEncoder.encode(compressString(path), "UTF8");
	}

	@Override
	public String toPath(String bookmark) throws Exception
	{
		return java.net.URLDecoder.decode(uncompressString(bookmark), "UTF8");
	}

	public String compressString(String srcTxt) throws IOException
	{
		ByteArrayOutputStream rstBao = new ByteArrayOutputStream();
        try (GZIPOutputStream zos = new GZIPOutputStream(rstBao)) {
		    zos.write(srcTxt.getBytes("UTF8"));
		    return Base64.encodeBase64String(rstBao.toByteArray());
        }
	}

	public static String uncompressString(String zippedBase64Str) throws IOException
	{
		String result = "";
		byte[] bytes = Base64.decodeBase64(zippedBase64Str);
		try (GZIPInputStream zi = new GZIPInputStream(new ByteArrayInputStream(bytes));)
		{
			result = IOUtils.toString(zi, Charset.forName("UTF8"));
		}
		return result;
	}
	
}
