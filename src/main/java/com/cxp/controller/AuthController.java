package com.cxp.controller;

import cn.hutool.core.map.MapUtil;
import com.cxp.common.lang.Const;
import com.cxp.common.lang.HttpResult;
import com.cxp.entity.SysUser;
import com.cxp.service.SysUserService;
import com.cxp.utils.RedisUtil;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.security.Principal;
import java.util.UUID;

/**
 * @Author: cxp
 * @Date: 2021/5/14
 * @Time: 14:58
 * @Description: 获取验证码
 */
@RestController
@Slf4j
public class AuthController extends BaseController {
    @Autowired
    private Producer producer;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired


    /**
     * 图片验证码
     **/
    @GetMapping("/captcha")
    public HttpResult captcha() throws Exception {
        String code = producer.createText();
        String key = UUID.randomUUID().toString();
        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        BASE64Encoder encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";
        String base64Img = str + encoder.encode(outputStream.toByteArray());
        //储存到redis中
        redisUtil.hset(Const.CAPTCHA_KEY, key, code, 120);
        log.info("验证码 -- {} - {}", key, code);
        return HttpResult.succ(MapUtil.builder()
                .put("token", key)
                .put("base64Img", base64Img).build());
    }

    /**
	 * 获取用户信息接口
	 * @param principal
	 * @return
	 */
	@GetMapping("/sys/userInfo")
	public HttpResult userInfo(Principal principal) {

		SysUser sysUser = sysUserService.getByUsername(principal.getName());

		return HttpResult.succ(MapUtil.builder()
				.put("id", sysUser.getId())
				.put("username", sysUser.getUsername())
				.put("avatar", sysUser.getAvatar())
				.put("created", sysUser.getCreated())
				.map()
		);
	}
}
