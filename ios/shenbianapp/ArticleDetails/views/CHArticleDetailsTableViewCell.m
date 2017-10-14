//
//  CHArticleDetailsTableViewCell.m
//  shenbianapp
//
//  Created by book on 2017/9/24.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHArticleDetailsTableViewCell.h"

@interface CHArticleDetailsTableViewCell ()

@property(nonatomic,strong)UILabel *titleLabel;
@property(nonatomic,strong)UIImageView *headImageView;
@property(nonatomic,strong)UILabel *userNameLabel;
@property(nonatomic,strong)UIButton *focusButton;
@property(nonatomic,strong)UILabel *commentLabel;
@property(nonatomic,strong)UIImageView *commentUserHead;
@property(nonatomic,strong)UILabel *commentUserName;
@property(nonatomic,strong)UILabel *commentContentLabel;
@property(nonatomic,strong)UIButton *praiseButton;
@property(nonatomic,strong)UIButton *commentButton;
@property(nonatomic,strong)UIScrollView *articleScrollView;

@end

@implementation CHArticleDetailsTableViewCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{

    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self addSubview:self.titleLabel];
        [self.titleLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self).offset(15);
            make.right.equalTo(self).offset(-15);
            make.top.equalTo(self).offset(5);
        }];
        
        [self addSubview:self.headImageView];
        [self.headImageView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self).offset(15);
            make.top.equalTo(self.titleLabel.mas_bottom).offset(15);
            make.width.height.mas_equalTo(40);
        }];
        
        [self addSubview:self.userNameLabel];
        [self.userNameLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.headImageView.mas_right).offset(12);
            make.centerY.equalTo(self.headImageView);
            make.height.mas_equalTo(20);
            make.width.mas_equalTo(120);
        }];
        
        [self addSubview:self.focusButton];
        [self.focusButton mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.equalTo(self).offset(-15);
            make.centerY.equalTo(self.headImageView);
            make.width.mas_equalTo(50);
            make.height.mas_equalTo(22);
        }];
        
        [self addSubview:self.articleScrollView];
        [self.articleScrollView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self).offset(15);
            make.right.equalTo(self).offset(-15);
            make.top.equalTo(self.headImageView.mas_bottom).offset(20);
            make.height.mas_equalTo(180);
        }];
        
        [self addSubview:self.commentLabel];
        [self.commentLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self.articleScrollView.mas_bottom).offset(15);
            make.left.equalTo(self).offset(15);
            make.height.mas_equalTo(20);
            make.width.mas_equalTo(100);
        }];
        
        [self addSubview:self.commentUserHead];
        [self.commentUserHead mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self.commentLabel.mas_bottom).offset(20);
            make.left.equalTo(self).offset(30);
            make.height.width.mas_equalTo(40);
        }];
        
        [self addSubview:self.commentUserName];
        [self.commentUserName mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.commentUserHead.mas_right).offset(10);
            make.centerY.equalTo(self.commentUserHead);
            make.height.mas_equalTo(20);
            make.width.mas_equalTo(100);
        }];
        
        [self addSubview:self.commentButton];
        [self.commentButton mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.equalTo(self).offset(-15);
            make.centerY.equalTo(self.commentUserHead);
           
        }];
        
        [self addSubview:self.praiseButton];
        [self.praiseButton mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.equalTo(self.commentButton.mas_left).offset(-30);
            make.centerY.equalTo(self.commentUserHead);
        }];
        
        [self addSubview:self.commentContentLabel];
        [self.commentContentLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self.commentUserHead.mas_bottom).offset(6);
            make.left.equalTo(self).offset(30);
            make.right.equalTo(self).offset(-30);
        }];
        
    }
    return self;

}

-(UILabel *)titleLabel{

    if (!_titleLabel) {
        _titleLabel = [UILabel new];
        _titleLabel.numberOfLines = 0;
        _titleLabel.font = [UIFont systemFontOfSize:20];
        _titleLabel.textColor = [UIColor colorWithHexString:@"#2d333a"];
        _titleLabel.text = @"标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题";
    }
    return _titleLabel;
}

-(UIImageView *)headImageView{

    if (!_headImageView) {
        _headImageView = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"sy_sj_cover"]];
        _headImageView.layer.cornerRadius = 20;
        _headImageView.clipsToBounds = YES;
    }
    return _headImageView;
}

-(UILabel *)userNameLabel{

    if (!_userNameLabel) {
        _userNameLabel = [UILabel new];
        _userNameLabel.textColor = [UIColor colorWithHexString:@"#4f5965"];
        _userNameLabel.text = @"作者昵称";
    }

    return _userNameLabel;
}

-(UIButton *)focusButton{

    if (!_focusButton) {
        _focusButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_focusButton setTitle:@"+关注" forState:(UIControlStateNormal)];
        [_focusButton setTitleColor:[UIColor colorWithHexString:@"#009698"] forState:(UIControlStateNormal)];
        _focusButton.titleLabel.font = [UIFont systemFontOfSize:12];
        _focusButton.layer.borderColor = [UIColor colorWithHexString:@"#009698"].CGColor;
        _focusButton.layer.borderWidth = 1;
        _focusButton.layer.cornerRadius = 11;
    }
    return _focusButton;
}

-(UIScrollView *)articleScrollView{

    if (!_articleScrollView) {
        _articleScrollView = [UIScrollView new];
        _articleScrollView.showsVerticalScrollIndicator = NO;
        _articleScrollView.showsHorizontalScrollIndicator = NO;
        _articleScrollView.backgroundColor = [UIColor colorWithHexString:@"f5e65e"];
    }
    return _articleScrollView;
}

-(UILabel *)commentLabel{

    if (!_commentLabel) {
        _commentLabel = [UILabel new];
        _commentLabel.text = @"评论（555）";
        _commentLabel.textColor = [UIColor colorWithHexString:@"#2d333a"];
        _commentLabel.font = [UIFont systemFontOfSize:15];
    }
    
    return _commentLabel;
}

-(UIImageView *)commentUserHead{

    if (!_commentUserHead) {
        _commentUserHead = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"sy_sj_cover"]];
        _commentUserHead.layer.cornerRadius = 20;
        _commentUserHead.clipsToBounds = YES;
    }
    return _commentUserHead;
}

-(UILabel *)commentUserName{

    if (!_commentUserName) {
        _commentUserName = [UILabel new];
        _commentUserName.text = @"评论者昵称";
        _commentUserName.textColor = [UIColor colorWithHexString:@"#2d333a"];
        _commentUserName.font = [UIFont systemFontOfSize:15];
    }

    return _commentUserName;
}

-(UIButton *)praiseButton{

    if (!_praiseButton) {
        _praiseButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_praiseButton setImage:[UIImage imageNamed:@"ydwz_dz"] forState:(UIControlStateNormal)];
    }
    return _praiseButton;
}

-(UIButton *)commentButton{

    if (!_commentButton) {
        _commentButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_commentButton setImage:[UIImage imageNamed:@"ydwz_plq"] forState:(UIControlStateNormal)];
    }
    return _commentButton;
}

-(UILabel *)commentContentLabel{

    if (!_commentContentLabel) {
        _commentContentLabel = [UILabel new];
        _commentContentLabel.text = @"评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容";
        _commentContentLabel.textColor = [UIColor colorWithHexString:@"#4f5965"];
        _commentContentLabel.font = [UIFont systemFontOfSize:15];
        _commentContentLabel.numberOfLines = 0;
    }
    return _commentContentLabel;

}

@end
