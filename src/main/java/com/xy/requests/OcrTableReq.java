package com.xy.requests;

import com.alibaba.fastjson.JSON;
import com.xy.responses.TableOcrResponse;
import com.xy.utils.image.ImageEncode;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * error_code：错误码。
 * error_msg：错误描述信息，帮助理解和解决发生的错误
 */

/**
 * 1	Unknown error	服务器内部错误，请再次请求，如果持续出现此类错误，请在控制台提交工单联系技术支持团队
 * 2	Service temporarily unavailable	服务暂不可用，请再次请求，如果持续出现此类错误，请在控制台提交工单联系技术支持团队
 * 3	Unsupported openapi method	调用的API不存在，请检查请求URL后重新尝试，一般为URL中有非英文字符，如"-"，可手动输入重试
 * 4	Open api request limit reached	集群超限额，请再次请求，如果持续出现此类错误，请在控制台提交工单联系技术支持团队
 * 6	No permission to access data	无权限访问该用户数据，创建应用时未勾选相关文字识别接口，请登录百度云控制台，找到对应的应用，编辑应用，勾选上相关接口后重新调用
 * 14	IAM Certification failed	IAM鉴权失败，建议用户参照文档自查生成sign的方式是否正确，或换用控制台中ak sk的方式调用
 * 17	Open api daily request limit reached	每天请求量超限额，已支持计费的接口，您可以在控制台文字识别服务选择购买相关接口的次数包或开通按量后付费；邀测和未支持计费的接口，您可以在控制台提交工单申请提升限额
 * 18	Open api qps request limit reached	QPS超限额，免费额度并发限制为2QPS，开通按量后付费或购买次数包后并发限制为10QPS，如您需要更多的并发量，可以选择购买QPS叠加包；邀测和未支持计费的接口，您可以在控制台提交工单申请提升限额
 * 19	Open api total request limit reached	请求总量超限额，已支持计费的接口，您可以在控制台文字识别服务选择购买相关接口的次数包或开通按量后付费；邀测和未支持计费的接口，您可以在控制台提交工单申请提升限额
 * 100	Invalid parameter	无效的access_token参数，token拉取失败，您可以参考“Access Token获取”文档重新获取
 * 110	Access token invalid or no longer valid	access_token无效，token有效期为30天，请注意需要定期更换，也可以每次请求都拉取新token
 * 111	Access token expired	access token过期，token有效期为30天，请注意需要定期更换，也可以每次请求都拉取新token
 * 216100	invalid param	请求中包含非法参数，请检查后重新尝试
 * 216101	not enough param	缺少必须的参数，请检查参数是否有遗漏
 * 216102	service not support	请求了不支持的服务，请检查调用的url
 * 216103	param too long	请求中某些参数过长，请检查后重新尝试
 * 216110	appid not exist	appid不存在，请重新核对信息是否为后台应用列表中的appid
 * 216200	empty image	图片为空，请检查后重新尝试
 * 216201	image format error	上传的图片格式错误，现阶段我们支持的图片格式为：PNG、JPG、JPEG、BMP，请进行转码或更换图片
 * 216202	image size error	上传的图片大小错误，现阶段我们支持的图片大小为：base64编码后小于4M，分辨率不高于4096*4096，请重新上传图片
 * 216202	input oversize	上传的包体积过大，现阶段不支持 10M 或以上的数据包
 * 216630	recognize error	识别错误，请再次请求，如果持续出现此类错误，请在控制台提交工单联系技术支持团队
 * 216631	recognize bank card error	识别银行卡错误，出现此问题的原因一般为：您上传的图片非银行卡正面，上传了异形卡的图片、上传的银行卡正面图片不完整或模糊
 * 216633	recognize idcard error	识别身份证错误，出现此问题的原因一般为：您上传了非身份证图片、上传的身份证图片不完整或模糊
 * 216634	detect error	检测错误，请再次请求，如果持续出现此类错误，请在控制台提交工单联系技术支持团队
 * 282000	internal error	服务器内部错误，如果您使用的是高精度接口，报这个错误码的原因可能是您上传的图片中文字过多，识别超时导致的，建议您对图片进行切割后再识别，其他情况请再次请求， 如果持续出现此类错误，请在控制台提交工单联系技术支持团队
 * 282003	missing parameters: {参数名}	请求参数缺失
 * 282005	batch processing error	处理批量任务时发生部分或全部错误，请根据具体错误码排查
 * 282006	batch task limit reached	批量任务处理数量超出限制，请将任务数量减少到10或10以下
 * 282100	image transcode error	图片压缩转码错误
 * 282102	target detect error	未检测到图片中识别目标，请确保图片中包含对应卡证票据，出现此问题的原因一般为：您上传了非卡证图片、图片不完整或模糊
 * 282103	target recognize error	图片目标识别错误，请确保图片中包含对应卡证票据，出现此问题的原因一般为：您上传了非卡证图片、图片不完整或模糊
 * 282110	urls not exit	URL参数不存在，请核对URL后再次提交
 * 282111	url format illegal	URL格式非法，请检查url格式是否符合相应接口的入参要求
 * 282112	url download timeout	url下载超时，请检查url对应的图床/图片无法下载或链路状况不好，或图片大小大于3M，或图片存在防盗链，您可以重新尝试一下，如果多次尝试后仍不行，建议更换图片地址
 * 282113	url response invalid	URL返回无效参数
 * 282114	url size error	URL长度超过1024字节或为0
 * 282808	request id: xxxxx not exist	request id xxxxx 不存在
 * 282809	result type error	返回结果请求错误（不属于excel或json）
 * 282810	image recognize error	图像识别错误，请再次请求，如果持续出现此类错误，请在控制台提交工单联系技术支持团队
 */

public class OcrTableReq extends OrcBasicReq{

    private final Logger logger = LoggerFactory.getLogger(OcrTableReq.class);
    private final String urlPrefix = "https://aip.baidubce.com/rest/2.0/solution/v1/form_ocr/request";

    public String request(String path) throws Exception {
        // step1 get url
        String url = getUrl(urlPrefix);
        // step2 gen params
        String encodedImage = ImageEncode.encode(path);
        PostMethod postMethod = new PostMethod(url);
        postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        NameValuePair[] data = {
                new NameValuePair("image", encodedImage),
                new NameValuePair("is_sync", "true"),
                new NameValuePair("request_type", "excel"),
        };
        postMethod.setRequestBody(data);
        // step3 send request
        HttpClient httpClient = new HttpClient();
        httpClient.executeMethod(postMethod);
        String result = new String(postMethod.getResponseBody(), "utf-8");
//        logger.info("TableOcrResponse: "  +  result);
        // step4 parse result
        TableOcrResponse resp;
        try {
            resp = JSON.parseObject(result, TableOcrResponse.class);
        } catch (Exception e) {
            logger.info("parse TableOcrResponse failed");
            e.printStackTrace();
            throw e;
        }
        if (resp.getErrorCode() != 0){
            throw new Exception("TableOcrResponse err: " + result);
        }
        // step5 check ret code
        if (resp.getResult().getRetCode() != 3){
            throw new Exception("OcrTableReq return code: " + resp.getResult().getRetCode());
        }
        return resp.getResult().getResultData();
    }
}
