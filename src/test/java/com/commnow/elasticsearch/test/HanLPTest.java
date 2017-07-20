package com.commnow.elasticsearch.test;

import java.util.List;

import org.junit.Test;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;
import com.hankcs.hanlp.suggest.Suggester;




public class HanLPTest {
	public static void main(String[] args) {
//		String content = "据TANBinfo网站7月6日利雅得报道，总部设在美国的陶氏化学公司日前宣布了投资1亿多美元在沙特阿拉伯建造一个新的现代化的制造设施来生产一系列用于涂料和水处理的聚合物的计划。这个位于朱拜勒塑料化工园的涂料设施将满足沙特市场对工业和建筑涂料和水处理以及洗涤剂应用的需求。";
//		List<String> keywordList = HanLP.extractKeyword(content, 10);
//		System.out.println(keywordList);
		
		String document = "【盖德化工网  企业新闻】沙特阿美(SaudiAramco)是全球最大的原油供应商，占全球供应量的十分之一。但现今，业内纷纷聚焦于其于陶氏(Dow)的合资公司Sadara。Sadara资产总值约200亿美元，位于朱拜尔(Jubail)工业中心内，一经投产，必将改变业内格局。Sadara设备建设耗时五年完工，是迄今单阶段内结成规模最大的生产设备，Sadara预计其产品的三分之一会在朱拜尔工业中心内消化。明年，Sadara将启动首次公开募股(IPO)，公司方面预计届时其市值将达2万亿美元，但分析师认为最终实际可达市值为1万亿美元。但IPO可能致使公司在投资者要求下，削弱原核心产业，即原油，相关业务占比。这是沙特阿美自其1980年国有化以来首次允许其相关企业引入海外资本，此举意在使公司进入化工领域，吸引潜在投资者，而非依赖于单一的石油生产，同时也促进沙特经济的多样化发展。阿美希望能在2030年成为全球领先的能源及化学品企业。就利润而言，每生产一吨化学品所带来的利润远超一吨精炼油。去年，阿美化学品产能已达2800万吨。如该项目进展顺利，未来几年内，Sadara将能够生产应用于沙发、肥皂等各个下游行业的乙二醇、异氰酸酯及其他化学品。但沙特阿美的雄心不止于此。2015年，阿美投资13.7亿美元，与德国朗盛(Lanxess)建立合资公司，生产弹性体，现今，该公司业务触及99个国家，2016年税前利润达3.73亿美元。现下，公司正与沙特基础工业公司(SaudiBasicIndustrialCorp.)磋商另一200亿美元公司投资计划。";
////		List<String> sentenceList = HanLP.extractSummary(document, 5);
////		System.out.println(sentenceList);
		List<String> keywordList = HanLP.extractKeyword(document, 10);
		System.out.println(keywordList);
		
		Suggester suggester = new Suggester();
        String[] titleArray =
        (
                "威廉王子发表演说 呼吁保护野生动物\n" +
                "国王的演讲这部电影你看过吗\n" +
                "《时代》年度人物最终入围名单出炉 普京马云入选\n" +
                "上海传声是一家B2B营销公司，他们主营业务之一就是危机公关\n" +
                "码云是一个像github一样的开源代码托管网站\n" +
                "“黑格比”横扫菲：菲吸取“海燕”经验及早疏散\n" +
                "日本保密法将正式生效 日媒指其损害国民知情权\n" +
                "英报告说空气污染带来“公共健康危机”"
        ).split("\\n");
        for (String title : titleArray)
        {
            suggester.addSentence(title);
        }

        System.out.println(suggester.suggest("公主", 2));       // 语义
        System.out.println(suggester.suggest("危机公共", 2));   // 字符
        System.out.println(suggester.suggest("mayun", 2));      // 拼音
	}
	
	@Test
	public void test(){
		String text = "重载不是重任";
        List<Pinyin> pinyinList = HanLP.convertToPinyinList(text);
        String word = "";
        for (Pinyin pinyin : pinyinList)
        {	
        	word += (""+pinyin);
        	
        }
        System.out.println(word);
	}
}
