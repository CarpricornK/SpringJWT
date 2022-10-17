package kopo.poly.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kopo.poly.dto.NoticeDTO;
import kopo.poly.repository.NoticeRepository;
import kopo.poly.repository.entity.NoticeEntity;
import kopo.poly.service.INoticeService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service("NoticeService")
public class NoticeService implements INoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public List<NoticeDTO> getNoticeList() {
        log.info(this.getClass().getName() + ".getNoticeList Start");

        List<NoticeEntity> rList = noticeRepository.findAllByOrderByNoticeSeqDesc();

        List<NoticeDTO> nList = new ObjectMapper().convertValue(rList,
                new TypeReference<List<NoticeDTO>>() {
                });

        log.info(this.getClass().getName() + ".getNoticeList End");

        return nList;
    }

    @Transactional
    @Override
    public NoticeDTO getNoticeInfo(NoticeDTO pDTO, boolean type) {

        log.info(this.getClass().getName() + ".getNoticeInfo Start");

        NoticeEntity rEntity = noticeRepository.findByNoticeSeq(pDTO.getNoticeSeq());

        if (type) {

            NoticeEntity pEntity = NoticeEntity.builder()
                    .noticeSeq(rEntity.getNoticeSeq()).title(rEntity.getTitle())
                    .noticeYn(rEntity.getNoticeYn()).contents(rEntity.getContents())
                    .userId(rEntity.getUserId())
                    .readCnt(rEntity.getReadCnt() + 1)
                    .regId(rEntity.getRegId()).regDt(rEntity.getRegDt())
                    .chgId(rEntity.getChgId()).chgDt(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"))
                    .build();

            noticeRepository.save(pEntity);

            rEntity = noticeRepository.findByNoticeSeq(pDTO.getNoticeSeq());

        }


        NoticeDTO rDTO = new ObjectMapper().convertValue(rEntity, NoticeDTO.class);

        log.info(this.getClass().getName() + ".getNoticeInfo End");

        return rDTO;
    }

    @Transactional
    @Override
    public void updateNoticeInfo(NoticeDTO pDTO) {
        log.info(this.getClass().getName() + ".updateNoticeInfo Start");

        String title = CmmUtil.nvl(pDTO.getTitle());
        String noticeYn = CmmUtil.nvl(pDTO.getNoticeYn());
        String contents = CmmUtil.nvl(pDTO.getContents());
        String userId = CmmUtil.nvl(pDTO.getUserId());

        log.info("title : " + title);
        log.info("noticeYn : " + noticeYn);
        log.info("contents : " + contents);
        log.info("userId : " + userId);

        NoticeEntity rEntity = noticeRepository.findByNoticeSeq(pDTO.getNoticeSeq());

        NoticeEntity pEntity = NoticeEntity.builder()
                .title(title)
                .noticeYn(noticeYn).contents(contents)
                .userId(userId)
                .readCnt(rEntity.getReadCnt())
                .regId(rEntity.getRegId()).regDt(rEntity.getRegDt())
                .chgId(userId).chgDt(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"))
                .build();

        noticeRepository.save(pEntity);


        log.info(this.getClass().getName() + ".updateNoticeInfo End");
    }


    @Transactional
    @Override
    public void deleteNoticeInfo(NoticeDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".deleteNoticeInfo Start");

        String noticeSeq = CmmUtil.nvl(pDTO.getNoticeSeq());
        log.info("noticeSeq : " + noticeSeq);

        noticeRepository.deleteById(noticeSeq);

        log.info(this.getClass().getName() + ".deleteNoticeInfo End");
    }

    @Transactional
    @Override
    public void InsertNoticeInfo(NoticeDTO pDTO) throws Exception{
        log.info(this.getClass().getName() + ".InsertNoticeInfo Start");

        String title = CmmUtil.nvl(pDTO.getTitle());
        String noticeYn = CmmUtil.nvl(pDTO.getNoticeYn());
        String contents = CmmUtil.nvl(pDTO.getContents());
        String userId = CmmUtil.nvl(pDTO.getUserId());

        log.info("title : " + title);
        log.info("noticeYn : " + noticeYn);
        log.info("contents : " + contents);
        log.info("userId : " + userId);

        NoticeEntity pEntity = NoticeEntity.builder()
               .title(title).noticeYn(noticeYn).contents(contents).userId(userId).readCnt(0L)
               .regId(userId).regDt(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"))
               .chgId(userId).chgDt(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"))
               .build();

        noticeRepository.save(pEntity);

        log.info(this.getClass().getName() + ".InsertNoticeInfo End");
    }


}
