package cn.hc.xiaosi.dto;

import cn.hc.xiaosi.entity.Record;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName RecordInputDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/515:37
 */
@Data
public class RecordInputDTO {
    /**
     * 关键字
     */
    private String keyword;

    /**
     * 正向转化
     *
     * @return
     */
    public Record convertToRecord() {
        RecordInputDTOConvert recordInputDTOConvert = new RecordInputDTOConvert();
        Record convert = recordInputDTOConvert.convert(this);
        return convert;
    }

    /**
     * 逆向转化
     *
     * @param record
     * @return
     */
    public RecordInputDTO convertFor(Record record) {
        RecordInputDTOConvert recordInputDTOConvert = new RecordInputDTOConvert();
        RecordInputDTO convert = recordInputDTOConvert.reverse().convert(record);
        return convert;
    }

    /**
     * 转换类及方法
     */
    private static class RecordInputDTOConvert extends Converter<RecordInputDTO, Record> {
        @Override
        protected Record doForward(RecordInputDTO recordInputDTO) {
            Record record = new Record();
            BeanUtils.copyProperties(recordInputDTO, record);
            return record;
        }

        @Override
        protected RecordInputDTO doBackward(Record record) {
            throw new AssertionError("不支持逆向转化方法");
        }
    }

}
