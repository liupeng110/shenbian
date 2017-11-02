//
//  MHMineHeaderCollectionReusableView.m
//  Miaohi
//
//  Created by 沈开洋 on 16/11/29.
//  Copyright © 2016年 haiqiu. All rights reserved.
//

#import "MHMineHeaderCollectionReusableView.h"
@interface MHMineHeaderCollectionReusableView()
@property (weak, nonatomic) IBOutlet UIButton *myArticleButton;
@property (weak, nonatomic) IBOutlet UIButton *myService;
@property (weak, nonatomic) IBOutlet UILabel *publishedNOLabel;
@property (weak, nonatomic) IBOutlet UILabel *collectedNOLabel;
@property (weak, nonatomic) IBOutlet UILabel *attentionNoLabel;
@property (weak, nonatomic) IBOutlet UILabel *fansNOLabel;

@end

@implementation MHMineHeaderCollectionReusableView

- (void)awakeFromNib {
    [super awakeFromNib];
    self.mhUserHeaderImgView.layer.masksToBounds = YES;
    self.mhUserHeaderImgView.layer.cornerRadius = 75.0/2.0;
    [self.focusAndFansView addSubview:self.attentionsBtn];
    [self.focusAndFansView addSubview:self.fansBtn];
    
   
    [self.myArticleButton addTarget:self action:@selector(clickMyArticleNButton:) forControlEvents:(UIControlEventTouchUpInside)];
    [self.myService addTarget:self action:@selector(clickMyServiceButton:) forControlEvents:(UIControlEventTouchUpInside)];

}

-(instancetype)initWithFrame:(CGRect)frame{

    if ([super initWithFrame:frame]) {
       
       
    }
    return self;
}

- (void)clickMyArticleNButton:(UIButton*)button{

    if (self.clickMyArticle) {
        self.clickMyArticle();
    }
    
}

- (void)clickMyServiceButton:(UIButton*)button{

    if (self.clickMyService) {
        self.clickMyService();
    }
}

//编辑个人资料101
//关注按钮102
//映答103
//关注数点击按钮104
//粉丝数点击按钮105
//更换背景图
//- (IBAction)clickUserHeaderViewVideoFocuAndAnswerBtnAction:(SLButton *)sender {
    //if ([self.delegate respondsToSelector:@selector(clickMineCentreHeaderViewWith:)]) {
        //[self.delegate clickMineCentreHeaderViewWith:sender];
    //}
//}

/*
- (void)creatMhHeaderViewithUserInfoModel:(PersonsetInfoModel *)personModel VieWith:(BOOL)isMyHomePage{
    if (isMyHomePage) {
        self.BottomHeigh.constant = 15;
        self.focusAndAttionBtnH.constant = 13;
        [self.editAndAttionBtn setTitleColor:[UIColor colorWithHexColor:@"#1d1d1d"] forState:UIControlStateNormal];
        [self.editAndAttionBtn setTitle:@"编辑资料" forState:UIControlStateNormal];
    }else{
        self.editAndAttionBtn.layer.borderWidth = 0.5;
        self.editAndAttionBtn.clipsToBounds = YES;
        self.editAndAttionBtn.layer.cornerRadius = 3;
        self.focusAndAttionBtnH.constant = 25;
        self.BottomHeigh.constant = 20;
        if ([personModel.attention_state isEqualToString:@"1"]) {
            self.editAndAttionBtn.layer.borderColor = [UIColor colorWithHexColor:@"#c4c4c4"].CGColor;
            [self.editAndAttionBtn setTitle:@"已关注" forState:UIControlStateNormal];
            [self.editAndAttionBtn setTitleColor:[UIColor colorWithHexColor:@"#c4c4c4"] forState:UIControlStateNormal];
            
        }else{
            self.editAndAttionBtn.layer.borderColor = [UIColor colorWithHexColor:@"#00a2ff"].CGColor;
            [self.editAndAttionBtn setTitle:@"关注" forState:UIControlStateNormal];
            [self.editAndAttionBtn setTitleColor:[UIColor colorWithHexColor:@"#00a2ff"] forState:UIControlStateNormal];
        }

}
    
    [self.mhBackImgView sd_setImageWithURL:[NSURL URLWithString:personModel.background_uri] placeholderImage:[UIImage imageNamed:@"2.4wodebeijing"]];
    self.mhUserNameLabel.text= personModel.user_name;
    if (personModel.user_authentic.length > 0) {
        self.mhUserNoteLabel.hidden = NO;
    }else{
        self.mhUserNoteLabel.hidden = YES;
    }
    self.mhUserNoteLabel.text = personModel.user_authentic;
    if (personModel.portrait_uri.length > 0 ) {
        [self.mhUserHeaderImgView sd_setImageWithURL:[NSURL URLWithString:personModel.portrait_uri] placeholderImage:[UIImage imageNamed:TouXiangPersonal]];
    }else{
        self.mhUserHeaderImgView.image = [UIImage imageNamed:TouXiangPersonal];
    }
    if ([personModel.user_gender integerValue] == 1) {
        self.mhUserGerdenImgView.hidden = NO;
        self.mhUserGerdenImgView.image = [UIImage imageNamed:@"nichengnan"];
    }else if([personModel.user_gender integerValue] == 2){
        self.mhUserGerdenImgView.hidden = NO;
        self.mhUserGerdenImgView.image = [UIImage imageNamed:@"nichengnv"];
    }else{
        self.mhUserGerdenImgView.hidden = YES;
    }
    self.mhUserGerdenImgView.layer.shouldRasterize = YES;
    //大V
    self.mhDavImgView.image = [UIImage imageNamed:@"putongtouxiangdaka"];
    
    if ([personModel.user_type integerValue] > 10) {
        self.mhDavImgView.hidden = NO;
    }else{
        self.mhDavImgView.hidden = YES;
    }
    NSString *attentionStr;
    NSString *fansStr;
    if (personModel.attention_count.length > 0) {
        if ([personModel.attention_count integerValue] > NumberOfCount) {
           attentionStr = [NSString stringWithFormat:@"关注 %.1f万",[personModel.attention_count floatValue] / NumberCount];
        }else{
            attentionStr =[NSString stringWithFormat:@"关注 %@",personModel.attention_count];
        }
    }else{
         attentionStr = @"关注 0";
    }
    
    if (personModel.fans_count.length > 0) {
        if ([personModel.fans_new_count integerValue] > NumberOfCount) {
            fansStr = [NSString stringWithFormat:@"粉丝 %.1f万",[personModel.fans_count floatValue] / NumberCount];
        }else{
            fansStr = [NSString stringWithFormat:@"粉丝 %@",personModel.fans_count];
        }
    }else{
       fansStr = @"粉丝 0";
    }
    
    NSDictionary *Numbutes = @{NSFontAttributeName:[UIFont systemFontOfSize:12.0]};
    CGFloat attentionlength = [attentionStr boundingRectWithSize:CGSizeMake(self.frame.size.width, MAXFLOAT) options:NSStringDrawingUsesLineFragmentOrigin attributes:Numbutes context:nil].size.width;
    [self.attentionsBtn setTitle:attentionStr forState:UIControlStateNormal];
    self.attentionsBtn.frame = CGRectMake(0, 0,attentionlength , 13);
    
    CGFloat fanslength = [fansStr boundingRectWithSize:CGSizeMake(self.frame.size.width, MAXFLOAT) options:NSStringDrawingUsesLineFragmentOrigin attributes:Numbutes context:nil].size.width;
    [self.fansBtn setTitle:fansStr forState:UIControlStateNormal];
    self.fansBtn.frame = CGRectMake(self.attentionsBtn.x+self.attentionsBtn.width+15, 0,fanslength+10 , 13);
}

- (LXButton*)attentionsBtn{
    if (!_attentionsBtn) {
        _attentionsBtn = [LXButton buttonWithType:UIButtonTypeCustom];
        _attentionsBtn.titleLabel.font = [UIFont systemFontOfSize:12.0];
        _attentionsBtn.frame = CGRectMake(11, 0, 100,13);
        [_attentionsBtn setTitleColor:[UIColor colorWithHexColor:@"#1d1d1d"] forState:UIControlStateNormal];
         [_attentionsBtn addTarget:self action:@selector(attentionsBtnClick:) forControlEvents:UIControlEventTouchUpInside];
        _attentionsBtn.tag = 1001;
    }
    return _attentionsBtn;
}

- (LXButton*)fansBtn{
    if (!_fansBtn) {
        _fansBtn = [LXButton buttonWithType:UIButtonTypeCustom];
        _fansBtn.titleLabel.font = [UIFont systemFontOfSize:12.0];
         _fansBtn.frame = CGRectMake(self.attentionsBtn.x+self.attentionsBtn.width+15, 0, 100,13);
        [_fansBtn setTitleColor:[UIColor colorWithHexColor:@"#1d1d1d"] forState:UIControlStateNormal];
        [_fansBtn addTarget:self action:@selector(fansBtnClick:) forControlEvents:UIControlEventTouchUpInside];
        _fansBtn.tag = 1000;
    }
    return _fansBtn;
}

- (void)fansBtnClick:(LXButton*)btn{
    if (self.delegate && [self.delegate respondsToSelector:@selector(clickMineCentreattionAndFnasWith:)]) {
        [self.delegate clickMineCentreattionAndFnasWith:btn];
    }
}

- (void)attentionsBtnClick:(LXButton*)btn{
    if (self.delegate && [self.delegate respondsToSelector:@selector(clickMineCentreattionAndFnasWith:)]) {
        [self.delegate clickMineCentreattionAndFnasWith:btn];
    }
}
- (IBAction)clickMineHomePageHeaderImgAction:(SLButton *)sender {
    if ([self.delegate respondsToSelector:@selector(clickMineCentreHeaderViewWith:)]) {
        [self.delegate clickMineCentreHeaderViewWith:sender];
    }

}
*/
@end
