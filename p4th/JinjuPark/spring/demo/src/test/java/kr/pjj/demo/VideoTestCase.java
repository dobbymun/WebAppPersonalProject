package kr.pjj.demo;

import kr.pjj.demo.entity.member.Member;
import kr.pjj.demo.entity.video.MemberSaveVideo;
import kr.pjj.demo.entity.video.Video;
import kr.pjj.demo.repository.member.MemberRepository;
import kr.pjj.demo.repository.video.MemberSaveVideoRepository;
import kr.pjj.demo.repository.video.VideoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class VideoTestCase {

    @Autowired
    private VideoRepository repository;

    @Autowired
    private MemberSaveVideoRepository saveVideoRepository;

    @Autowired
    MemberRepository memberRepository;


    @Test
    void saveVideoSrc(){
        Video video1 = new Video("https://www.youtube.com/embed/tQ3O5SLNgK4");
        Video video2 = new Video("https://www.youtube.com/embed/bfaLzn_EUqk");
        Video video3 = new Video("https://www.youtube.com/embed/rIfa4moq2tw");
        Video video4 = new Video("https://www.youtube.com/embed/5Yk7zBBpA8Q");
        Video video5 = new Video("https://www.youtube.com/embed/GZtB7W9Uafk");
        Video video6 = new Video("https://www.youtube.com/embed/URVOrAxBDq0");
        Video video7 = new Video("https://www.youtube.com/embed/bce7hAfUmF8");
        Video video8 = new Video("https://www.youtube.com/embed/hhGOu8G00K0");
        Video video9 = new Video("https://www.youtube.com/embed/QSCeJ__akl0");
        Video video10 = new Video("https://www.youtube.com/embed/2hg7dbVtdDs");
        Video video11 = new Video("https://www.youtube.com/embed/wTpwLsPUNZE");
        Video video12 = new Video("https://www.youtube.com/embed/gREjsF6hyxk");
        repository.save(video1);
        repository.save(video2);
        repository.save(video3);
        repository.save(video4);
        repository.save(video5);
        repository.save(video6);
        repository.save(video7);
        repository.save(video8);
        repository.save(video9);
        repository.save(video10);
        repository.save(video11);
        repository.save(video12);
    }

    @Test
    void videoList(){
        List<Video> videoList= repository.findAll();
        System.out.println(videoList);
    }


    @Test
    void myVideoSaveAndCancel(){
        Long memberId = 1L;
        Long videoId = 1L;

        Optional<MemberSaveVideo> maybeMyVideoList = saveVideoRepository.findByMemberIdAndVideoId(memberId, videoId);

        if (maybeMyVideoList.equals(Optional.empty())) {
            MemberSaveVideo memberSaveVideo = new MemberSaveVideo();

            Optional<Member> maybeMember = memberRepository.findById(memberId);
            Member member = maybeMember.get();

            Optional<Video> maybeVideo = repository.findById(videoId);
            Video video = maybeVideo.get();

            memberSaveVideo.setMember(member);
            memberSaveVideo.setVideo(video);
            saveVideoRepository.save(memberSaveVideo);
            //비디오가 My Video List에 저장되었습니다


        } else {
            saveVideoRepository.deleteById(maybeMyVideoList.get().getId());
            //비디오가 My Video List에서 삭제되었습니다
        }
        //저장되었는 지 삭제되었는 지 결과 확인
        List<MemberSaveVideo> myVideoList = saveVideoRepository.findMemberSaveVideoByMemberId(memberId);
        System.out.println("나의 비디오 리스트: "+myVideoList);
    }


    @Test
    void myVideoRead(){
        Long memberId = 1L;

        List<MemberSaveVideo> myVideoList = saveVideoRepository.findMemberSaveVideoByMemberId(memberId);

        if (myVideoList.equals(Optional.empty())) {
            System.out.println("저장한 비디오 목록이 없어요");
        }

        System.out.println("나의 비디오 리스트: "+ myVideoList);

    }
}
