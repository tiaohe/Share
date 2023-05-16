package Template.RedisTemplate;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
*
*1）@EnableCaching，开启缓存功能。

2）@Cacheable，调用方法前，去缓存中找，找到就返回，找不到就执行方法，并将返回值放到缓存中。

3）@CachePut，方法调用前不会去缓存中找，无论如何都会执行方法，执行完将返回值放到缓存中。

4）@CacheEvict，清理缓存中的一个或多个记录。
*
* */
@Controller
@Api(tags = "标签")
@RequestMapping("/postTag")
public class useNote {
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    @ResponseBody
//    @ApiOperation("修改标签")
//    @CachePut(value = "codingmore", key = "'codingmore:postag:'+#postAddTagParam.postTagId")
//    public ResultObject<String> update(@Valid PostTagParam postAddTagParam) {
//        if (postAddTagParam.getPostTagId() == null) {
//            return ResultObject.failed("标签id不能为空");
//        }
//        PostTag postTag = postTagService.getById(postAddTagParam.getPostTagId());
//        if (postTag == null) {
//            return ResultObject.failed("标签不存在");
//        }
//        QueryWrapper<PostTag> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("description", postAddTagParam.getDescription());
//        int count = postTagService.count(queryWrapper);
//        if (count > 0) {
//            return ResultObject.failed("标签名称已存在");
//        }
//        BeanUtils.copyProperties(postAddTagParam, postTag);
//        return ResultObject.success(postTagService.updateById(postTag) ? "修改成功" : "修改失败");

//}
//    @CachePut(value = "codingmore", key = "'codingmore:postag:'+#postAddTagParam.postTagId")
//    value：缓存名称，也就是缓存的命名空间，value 这里应该换成 namespace 更好一点；
//    key：用于在命名空间中缓存的 key 值，可以使用 SpEL 表达式，比如说 'codingmore:postag:'+#postAddTagParam.postTagId；
//    还有两个属性 unless 和 condition 暂时没用到，分别表示条件符合则不缓存，条件符合则缓存
}


