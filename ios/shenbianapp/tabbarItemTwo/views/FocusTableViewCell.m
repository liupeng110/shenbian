//
//  FocusTableViewCell.m
//  shenbianapp
//
//  Created by 杨绍智 on 17/7/15.
//  Copyright © 2017年 杨绍智. All rights reserved.
//

#import "FocusTableViewCell.h"

@implementation FocusTableViewCell
- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        [self addSubview:self.headImage];
        [self addSubview:self.perName];
        [self addSubview:self.stateBtn];
        [self addSubview:self.FocusContentView];
        [self.FocusContentView addSubview:self.FocusContenImage];
        [self.FocusContentView addSubview:self.FocusContenTitle];
        [self.FocusContentView addSubview:self.FocusContenDressLogo];
        [self.FocusContentView addSubview:self.FocusContenDress];
        [self.FocusContentView addSubview:self.FocusContenNumLogo];
        [self.FocusContentView addSubview:self.FocusContenNum];
        [self.FocusContentView addSubview:self.FocusContenNote];
        
    }
    return self;
}

- (UIImageView*)headImage{
    if (!_headImage) {
        _headImage = [[UIImageView alloc]initWithFrame:CGRectMake(15, 15, 40, 40)];
        _headImage.clipsToBounds = YES;
        _headImage.layer.cornerRadius = 20.0;
        _headImage.backgroundColor = [UIColor colorWithHexColor:@"#dfdfdf"];
    }
    return _headImage;
}

- (UILabel*)perName{
    if (!_perName) {
        _perName = [[UILabel alloc]initWithFrame:CGRectMake(self.headImage.x+self.headImage.width+10, 0, kScreenWidth-15-40-10-15-50, 20)];
        _perName.textColor = [UIColor colorWithHexColor:@"#1d1d1d"];
        _perName.font = [UIFont systemFontOfSize:15.0];
        _perName.centerY = self.headImage.centerY;
        _perName.text = @"用户名";
    }
    return _perName;
}
- (LXButton*)stateBtn{
    if (!_stateBtn) {
        _stateBtn = [LXButton buttonWithType:UIButtonTypeCustom];
        _stateBtn.frame = CGRectMake(kScreenWidth-15-50, 0, 50, 25);
        _stateBtn.clipsToBounds = YES;
        _stateBtn.layer.borderWidth = 0.5;
        _stateBtn.layer.cornerRadius = 2.0;
        _stateBtn.titleLabel.font = [UIFont systemFontOfSize:12.0];
        _stateBtn.centerY = self.headImage.centerY;
        [_stateBtn setTitleColor:[UIColor lightGrayColor] forState:UIControlStateNormal];
        [_stateBtn setTitleColor:[UIColor lightGrayColor] forState:UIControlStateSelected];
        [_stateBtn setTitle:@"关注" forState:UIControlStateNormal];
        [_stateBtn setTitle:@"已关注" forState:UIControlStateSelected];
        _stateBtn.layer.borderColor = [UIColor colorWithHexColor:@"#dfdfdf"].CGColor;
        [_stateBtn addTarget:self action:@selector(stateBtnEnvent:) forControlEvents:UIControlEventTouchUpInside];
    }
    return _stateBtn;
}

- (UIView*)FocusContentView{
    if (!_FocusContentView) {
        _FocusContentView = [[UIView alloc]initWithFrame:CGRectMake(15, self.headImage.y+self.headImage.height+15, kScreenWidth-30, 130)];
        _FocusContentView.backgroundColor = [UIColor whiteColor];
        _FocusContentView.layer.masksToBounds = NO;
        //_FocusContentView.layer.cornerRadius = 5.f;
        _FocusContentView.layer.shadowOffset = CGSizeMake(.0f,2.5f);
        _FocusContentView.layer.shadowRadius = 1.5f;
        _FocusContentView.layer.shadowOpacity = .9f;
        _FocusContentView.layer.shadowColor = [UIColor colorWithHexColor:@"#dfdfdf"].CGColor;
        _FocusContentView.layer.shadowPath = [UIBezierPath bezierPathWithRect:_FocusContentView.bounds].CGPath;
    }
    return _FocusContentView;
}

- (UIImageView*)FocusContenImage{
    if (!_FocusContenImage) {
        _FocusContenImage = [[UIImageView alloc]initWithFrame:CGRectMake(0, 0, 130, 130)];
        _FocusContenImage.backgroundColor = [UIColor colorWithHexColor:@"#dfdfdf"];
    }
    return _FocusContenImage;
}
- (UILabel*)FocusContenTitle{
    if (!_FocusContenTitle) {
        _FocusContenTitle = [[UILabel alloc]initWithFrame:CGRectMake(self.FocusContenImage.x+self.FocusContenImage.width+8, 5, kScreenWidth-15-(self.FocusContenImage.x+self.FocusContenImage.width+8)-15, 20)];
        _FocusContenTitle.textColor = [UIColor blackColor];
        _FocusContenTitle.font = [UIFont boldSystemFontOfSize:14.0];
        _FocusContenTitle.text = @"标题标题标题";
    }
    return _FocusContenTitle;
}

- (UIImageView*)FocusContenDressLogo{
    if (!_FocusContenDressLogo) {
        _FocusContenDressLogo = [[UIImageView alloc]initWithFrame:CGRectMake(self.FocusContenImage.x+self.FocusContenImage.width+8, self.FocusContenImage.height-5-15, 12, 15)];
        _FocusContenDressLogo.image = [UIImage imageNamed:@"dressLogo"];
    }
    return _FocusContenDressLogo;
}

- (UIImageView*)FocusContenNumLogo{
    if (!_FocusContenNumLogo) {
        _FocusContenNumLogo = [[UIImageView alloc]initWithFrame:CGRectMake(self.FocusContentView.width-20-5-15, self.FocusContenImage.height-5-15, 15, 15)];
        _FocusContenNumLogo.image = [UIImage imageNamed:@"2.4meizan"];
    }
    return _FocusContenNumLogo;
}
- (UILabel*)FocusContenNum{
    if (!_FocusContenNum) {
        _FocusContenNum = [[UILabel alloc]initWithFrame:CGRectMake(self.FocusContentView.width-20, self.FocusContenImage.height-5-15, 20, 15)];
        _FocusContenNum.textColor = [UIColor colorWithHexColor:@"#999999"];
        _FocusContenNum.font = [UIFont boldSystemFontOfSize:10.0];
        _FocusContenNum.text = @"36";
    }
    return _FocusContenNum;
}
- (UILabel*)FocusContenDress{
    if (!_FocusContenDress) {
        _FocusContenDress = [[UILabel alloc]initWithFrame:CGRectMake(self.FocusContenDressLogo.x+self.FocusContenDressLogo.width+5, self.FocusContenImage.height-5-15, 80, 15)];
        _FocusContenDress.textColor = [UIColor colorWithHexColor:@"#999999"];
        _FocusContenDress.font = [UIFont boldSystemFontOfSize:10.0];
        _FocusContenDress.text = @"位置文字描述";
    }
    return _FocusContenDress;
}

- (UILabel*)FocusContenNote{
    if (!_FocusContenNote) {
        _FocusContenNote = [[UILabel alloc]initWithFrame:CGRectMake(self.FocusContenImage.x+self.FocusContenImage.width+8, self.FocusContenTitle.height+self.FocusContenTitle.y, kScreenWidth-30-130-8, 130-45-5)];
        _FocusContenNote.textColor = [UIColor colorWithHexColor:@"#1d1d1d"];
        _FocusContenNote.numberOfLines =0;
        _FocusContenNote.adjustsFontSizeToFitWidth = YES;
        _FocusContenNote.font = [UIFont systemFontOfSize:12.0];
        _FocusContenNote.text = @"正文内容正文内容正文内容正文内容正文内容正文内容正文内容正文内容正文内容正文内容正文内容正文内容正文内容正文内容正文内容正文内容正文内容正文内容正文内容正";
    }
    return _FocusContenNote;
}

- (void)stateBtnEnvent:(LXButton*)btn{
    btn.selected = !btn.isSelected;
}
- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
