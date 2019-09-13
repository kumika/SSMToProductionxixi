package Com.CB.Production.Mapper;

import Com.CB.Production.domain.UnqualifyApply;
import Com.CB.Production.domain.UnqualifyApplyExample;
import Com.CB.Production.domain.vo.UnqualifyApplyVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UnqualifyApplyMapper {
    long countByExample(UnqualifyApplyExample example);

    int deleteByExample(UnqualifyApplyExample example);

    int deleteByPrimaryKey(String unqualifyApplyId);

    int insert(UnqualifyApply record);

    int insertSelective(UnqualifyApply record);

    List<UnqualifyApply> selectByExample(UnqualifyApplyExample example);

    UnqualifyApply selectByPrimaryKey(String unqualifyApplyId);

    int updateByExampleSelective(@Param("record") UnqualifyApply record, @Param("example") UnqualifyApplyExample example);

    int updateByExample(@Param("record") UnqualifyApply record, @Param("example") UnqualifyApplyExample example);

    int updateByPrimaryKeySelective(UnqualifyApply record);

    int updateByPrimaryKey(UnqualifyApply record);

    
    
    
    
    
    List<UnqualifyApplyVO> find(UnqualifyApply unqualifyApply);

    int updateNote(UnqualifyApply unqualify);

    int deleteBatch(String[] ids);

    List<UnqualifyApplyVO> searchUnqualifyByUnqualifyId(String unqualifyId);

    List<UnqualifyApplyVO> searchUnqualifyByProductName(String productName);
}