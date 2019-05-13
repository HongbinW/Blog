package com.example.elasticsearch.repository.es;

import com.example.elasticsearch.domain.es.EsBlog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//ES资源库接口测试
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsBlogRepositoryTest {

    @Autowired
    private EsBlogRepository esBlogRepository;

    @Before //在执行Test之前会执行Before内的内容
    public void initRepositoryData(){
        //清除所有数据
        esBlogRepository.deleteAll();
        esBlogRepository.save(new EsBlog("登鹳雀楼","王之涣的登鹳雀楼",
                "白日依山尽，黄河入海流，欲求千里目，更上一层楼。"));
        esBlogRepository.save(new EsBlog("相思","王维的相思",
                "红豆生南国，春来发几枝。愿君多采撷，此物最相思。"));
        esBlogRepository.save(new EsBlog("静夜思","李白的静夜思",
                "床前明月光，疑是地上霜。举头望明月，低头思故乡。"));
    }

    //根据关键字全文搜索，会从title、summary、content三个地方只要有一个搜索到就显示
    //分页查询博客（带去重）
    @Test
    public void testindDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(){
        Pageable pageable = new PageRequest(0,20);
        String title = "思";
        String summary = "相思";
        String content = "相思";
        Page<EsBlog> page = esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(title,summary,content,pageable);
        assertThat(page.getTotalElements()).isEqualTo(2);

        System.out.println("-------start---------");
        for(EsBlog blog:page.getContent()){
            System.out.println(blog.toString());
        }
        System.out.println("-------end-----------");
    }

}
