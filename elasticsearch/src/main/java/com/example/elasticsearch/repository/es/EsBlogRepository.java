package com.example.elasticsearch.repository.es;

import com.example.elasticsearch.domain.es.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//ES资源库接口

public interface EsBlogRepository extends ElasticsearchRepository<EsBlog,String> {
    //根据关键字全文搜索，会从title、summary、content三个地方只要有一个搜索到就显示
    //分页查询博客（带去重）
    Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(String title, String summary, String content, Pageable pageable);

}
