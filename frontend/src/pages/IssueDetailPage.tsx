import React, { useEffect, useState, useRef } from 'react'
import styled from 'styled-components';

import ResponsiveLayout from '../components/common/ResponsiveLayout';

import API from 'lib/API/API';

const IssueDetailPage = () => {
  
  const [fetchedIssueInfo, setIssueInfo] = useState<any>({}); 
  const $OutputTextBox = useRef<any>()

  useEffect(() => {
    const fetchIssueDetailData = async () => {
      const target = window.location.pathname.split("/").slice(-1)[0];
      if (isNaN(target as any)) return;
      const response = await API.get.issuesDetail(target);
      setIssueInfo(response);
    }
    fetchIssueDetailData();
  }, []);


  const countSharpNum = (str) => {
    let count = 0;
    str.split('').forEach(letter => { if (letter === "#") count++; });
    return count;
  }

  const createTextBlock = (comment) => {
    console.log("createTextBlock", comment)
    const textObj = { tag: '', contents: comment };
  
    switch (textObj.contents[0]) {
      case "#":
        const num = countSharpNum(textObj.contents);
        textObj.tag = `<h${num}>${textObj.contents.slice(num)}</h${num}>`;
        break;
      case "-":
        textObj.tag = `<li>${textObj.contents.slice(1)}</li>`;
        break;
      default:
        textObj.tag = `<p>${textObj.contents}</p>`;
        break;
    }
    console.log(textObj.tag)
    return textObj.tag;
  }
  const parseCommentRenderBlock = (content) => {
    return content.split("\n").map((comment) => {
      return createTextBlock(comment);
    })
  }


  if (Object.keys(fetchedIssueInfo).length === 0) return <></>;
  return (
    <AddIssueLayout>
      <TitleBlock>
        <AddIssueTitle>{fetchedIssueInfo.issueDTO.issueInfo.title} #{fetchedIssueInfo.issueDTO.issueInfo.issueId}</AddIssueTitle>
      </TitleBlock>
      <ProfilePictureBlock>

      </ProfilePictureBlock>
      <ContentBlock>
        {fetchedIssueInfo.commentDTO.map((comment, i) => {
          return (
            <ContentRow key={`comment-${i}`}>
              <ProfileLayer>
                <ProfileImg src={comment.userDTO.profileImage} />
              </ProfileLayer>
              <CommentLayer>
                <CommentOutput>
                  {parseCommentRenderBlock(comment.content).map((comment) => {
                    return <div dangerouslySetInnerHTML={{__html: comment}}></div>
                  })}
                </CommentOutput>
              </CommentLayer>
            </ContentRow>
          );
        })}
      </ContentBlock>
    </AddIssueLayout>
  )
}

const AddIssueLayout = styled(ResponsiveLayout)`
  background-color: #F7F7FC;

  display: flex;
  flex-direction: column;

  width: 100%;
  height: 100%;
  min-height: 100vh;
  position: relative;
`
const TitleBlock = styled.div`
`
const ProfilePictureBlock = styled.div``

const ContentBlock = styled.div`
  margin-top: 3.2rem;
  display: flex;
  flex-direction: column;
`;
const ContentRow = styled.div`
  width: 100%;
  display: flex;
  
`;

const AddIssueTitle = styled.div`
  font-size: var(--TitleFontSize);
  width: 100%;
  margin-bottom: 3.2rem;

  &::after {
    content: "";
    position: absolute;
    background-color: #D9DBE9;
    height: 1px;
    width: 128rem;
    top: 5.6rem;
    left: 8rem;
  }
`
const InputLayer = styled(ResponsiveLayout)`
  width: 100%;
  display: flex;
  flex-direction: column;
  padding: 0 80px;
`
const TitleInput = styled.input`
  height: 5.6rem;
  margin-bottom: 1.6rem;
  border-radius: 1.4rem;
  background-color: #EFF0F6;
  border: none;
  padding: 0 2.4rem;
`

const CommentLayer = styled.div`
  width: 100%;
  margin-bottom: 1.6rem;

  display: flex;
  flex-direction: column;
`

const AddFileLayer = styled.div`
  border-radius: 1.4rem;
  background-color: #EFF0F6;
  border: none;
  padding: 2.4rem;
  color: #6E7191;
`

const CommentInput = styled.textarea`
  width: 50%;
  height: 34.3rem;
  border-radius: 1.4rem;
  background-color: #EFF0F6;
  border: none;
  padding: 2.4rem;
`

const CommentOutput = styled.div`
  border-radius: 1.4rem;
  background-color: #EFF0F6;
  border: none;
  padding: 2.4rem;
  margin-left: 2rem;
`

const OptionBlock = styled.div`
  display: flex;
  flex-direction: column;

  width: 30.8rem;
  border-radius: 16px;
  border: 1px solid #D9DBE9;

  font-weight: bold;
  line-height: 28px;
  color: #6E7191;
  background-color: #FEFEFE;

  & > div {
    padding: 3.2rem 3.4rem;
  }

`

const OptionLayer = styled.div`
  display: flex;
  width: 22.4rem;

  & > button {
    margin-left: auto;
    border: none;
    background-color: transparent;
    cursor: pointer;
  }

  & + & {
    border-top: 1px solid #D9DBE9;
  }
`;

const ProfileImg = styled.img`
  width: 50px;
  height: 50px;
  border-radius: 50%;
`;

const ProfileLayer = styled.div`
`;
const CancelButton = styled.button`
  border: none;
  background-color: transparent;
  width: 100px;
  height: 32px;
  color: #6E7191;
  cursor: pointer;
  font-weight: bold;

  position: absolute;
  top: 74rem;
  left: 100rem;

`

const UploadButton = styled.button`
  width: 24rem;
  height: 5.6rem;

  border-radius: 20px;
  border: none;
  cursor: pointer;
  font-weight: bold;
  color: #FEFEFE;
  background-color: #007AFF;
  opacity: 0.5;

  position: absolute;
  top: 73rem;
  left: 112rem;

  &:hover {
    background-color: #007AFF;
    opacity: 1;
  }
`

export default IssueDetailPage;
